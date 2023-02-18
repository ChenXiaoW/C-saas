package com.chenw.user.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenw.base.common.core.constant.ServiceNameConstants;
import com.chenw.base.common.core.context.SecurityContextHolder;
import com.chenw.base.common.core.enums.BaseCodeEnum;
import com.chenw.base.common.core.exception.BussionessException;
import com.chenw.base.common.core.exception.NoDataException;
import com.chenw.base.common.core.utils.PasswordEncoderUtil;
import com.chenw.user.api.dto.UserDetailDTO;
import com.chenw.user.api.entity.SysRoleInfo;
import com.chenw.user.api.entity.SysUserInfo;
import com.chenw.user.api.entity.SysUserRelateRole;
import com.chenw.user.biz.dao.SysUserInfoDao;
import com.chenw.user.biz.service.SysMenuRelatePermissionService;
import com.chenw.user.biz.service.SysRoleRelateMenuService;
import com.chenw.user.biz.service.SysUserInfoService;
import com.chenw.user.biz.service.SysUserRelateRoleService;
import com.chenw.user.biz.vo.InsertOrUpdateSysUserInfoVO;
import com.chenw.user.biz.vo.SysUserInfoPageQueryVO;
import com.chenw.user.biz.vo.SysUserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenw
 * @since 2023-01-07
 */
@Service
@RequiredArgsConstructor
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoDao, SysUserInfo> implements SysUserInfoService {

    private final SysMenuRelatePermissionService sysMenuRelatePermissionService;

    private final SysUserRelateRoleService sysUserRelateRoleService;

    private final SysRoleRelateMenuService sysRoleRelateMenuService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertSysUserInfo(InsertOrUpdateSysUserInfoVO vo) {

        Integer count = baseMapper.selectCount(Wrappers.<SysUserInfo>lambdaQuery().and(e -> e.eq(SysUserInfo::getAccount, vo.getAccount())
                .or().eq(SysUserInfo::getMobile, vo.getMobile())));
        if (count !=0){
            throw new BussionessException(ServiceNameConstants.USER_SERVICE, BaseCodeEnum.DATA_EXISTS.getCode(),new String[]{vo.getAccount(),vo.getMobile()},BaseCodeEnum.DATA_EXISTS.getMessage());
        }
        String salt = PasswordEncoderUtil.generateSalt();
        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setAccount(vo.getAccount());
        sysUserInfo.setGender(vo.getGender());
        sysUserInfo.setAvatar(vo.getAvatar());
        sysUserInfo.setMobile(vo.getMobile());
        sysUserInfo.setCreateBy(SecurityContextHolder.getUserId());
        sysUserInfo.setSalt(salt);
        sysUserInfo.setPassword(PasswordEncoderUtil.generatePassword(vo.getPassword(),salt));
        baseMapper.insert(sysUserInfo);
        if (CollUtil.isNotEmpty(vo.getRoleIds())){
            for (String roleId : vo.getRoleIds()) {
                SysUserRelateRole userRelateRole = new SysUserRelateRole();
                userRelateRole.setUserId(sysUserInfo.getId());
                userRelateRole.setRoleId(roleId);
                userRelateRole.setCreateBy(SecurityContextHolder.getUserId());
                sysUserRelateRoleService.save(userRelateRole);
            }
        }
        return sysUserInfo.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateSysUserInfo(String userId, InsertOrUpdateSysUserInfoVO vo) {
        Integer count = baseMapper.selectCount(Wrappers.<SysUserInfo>lambdaQuery().ne(SysUserInfo::getId,userId)
                .and(e -> e.eq(SysUserInfo::getAccount, vo.getAccount()).or().eq(SysUserInfo::getMobile, vo.getMobile())));
        if (count !=0){
            throw new BussionessException(ServiceNameConstants.USER_SERVICE, BaseCodeEnum.DATA_EXISTS.getCode(),new String[]{vo.getAccount(),vo.getMobile()},BaseCodeEnum.DATA_EXISTS.getMessage());
        }
        String currentUserId = SecurityContextHolder.getUserId();
        SysUserInfo sysUserInfo = this.getUserById(userId);
        baseMapper.update(null, Wrappers.<SysUserInfo>lambdaUpdate()
                .set(SysUserInfo::getAccount,vo.getAccount())
                .set(SysUserInfo::getUserName,vo.getUserName())
                .set(SysUserInfo::getGender,vo.getGender())
                .set(SysUserInfo::getAvatar,vo.getAvatar())
                .set(SysUserInfo::getMobile,vo.getMobile())
                .set(SysUserInfo::getState,vo.getState())
                .set(SysUserInfo::getPassword,PasswordEncoderUtil.generatePassword(vo.getPassword(),sysUserInfo.getSalt()))
                .set(SysUserInfo::getUpdateBy,currentUserId));
        sysUserRelateRoleService.update(null,Wrappers.<SysUserRelateRole>lambdaUpdate()
                .set(SysUserRelateRole::getDelFlag,true)
                .set(SysUserRelateRole::getUpdateBy,currentUserId)
                .eq(SysUserRelateRole::getUserId,userId));
        Optional.of(vo.getRoleIds()).orElse(new ArrayList<>()).forEach(item ->{
            SysUserRelateRole userRelateRole = new SysUserRelateRole();
            userRelateRole.setUserId(sysUserInfo.getId());
            userRelateRole.setRoleId(item);
            userRelateRole.setCreateBy(currentUserId);
            sysUserRelateRoleService.save(userRelateRole);
        });
        return userId;
    }

    @Override
    public UserDetailDTO queryUserDetailToSecurity(String userId) {
        SysUserInfo sysUserInfo = baseMapper.selectById(userId);
        if (null ==sysUserInfo){
            return null;
        }
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        BeanUtils.copyProperties(sysUserInfo,userDetailDTO);
        List<SysRoleInfo> sysRoleInfos = sysUserRelateRoleService.queryRolesByUserId(userId);
        if (CollUtil.isEmpty(sysRoleInfos)){
            return userDetailDTO;
        }
        Set<String> menuIds = sysRoleRelateMenuService.queryMenuIdsByRoleIds(sysRoleInfos.stream().map(SysRoleInfo::getId).collect(Collectors.toList()));
        if (CollUtil.isEmpty(menuIds)){
            return userDetailDTO;
        }
        Set<String> permissionCodeByMenuIds = sysMenuRelatePermissionService.queryPermissionCodeByMenuIds(menuIds);
        if (CollUtil.isEmpty(permissionCodeByMenuIds)){
            return userDetailDTO;
        }
        userDetailDTO.setUserPermissions(permissionCodeByMenuIds);
        return userDetailDTO;
    }

    @Override
    public Page<SysUserInfoVO> queryPage(SysUserInfoPageQueryVO vo) {
        Page<SysUserInfoVO> sysUserInfoVOPage = baseMapper.queryPage(new Page<>(vo.getCurrent(), vo.getSize()), vo);
        if (CollUtil.isEmpty(sysUserInfoVOPage.getRecords())){
            return sysUserInfoVOPage;
        }
        sysUserInfoVOPage.getRecords().forEach(item ->{
            SysUserInfo sysUserInfo = baseMapper.selectById(item.getCreateBy());
            if (sysUserInfo != null){
                item.setCreateByName(sysUserInfo.getUserName());
            }
        });
        return baseMapper.queryPage(new Page<>(vo.getCurrent(),vo.getSize()),vo);
    }

    @Override
    public SysUserInfoVO queryDetail(String id) {
        SysUserInfo sysUserInfo = getUserById(id);
        SysUserInfoVO sysUserInfoVO = new SysUserInfoVO();
        BeanUtils.copyProperties(sysUserInfo,sysUserInfoVO);
        if (StrUtil.isNotEmpty(sysUserInfo.getCreateBy())){
            SysUserInfo createUserInfo = baseMapper.selectById(sysUserInfo.getCreateBy());
            sysUserInfoVO.setCreateByName(createUserInfo==null?null:createUserInfo.getUserName());
        }
        if (StrUtil.isNotEmpty(sysUserInfo.getUpdateBy())){
            SysUserInfo updateUserInfo = baseMapper.selectById(sysUserInfo.getUpdateBy());
            sysUserInfoVO.setUpdateByName(updateUserInfo==null?null:updateUserInfo.getUserName());
        }
        List<SysRoleInfo> sysRoleInfos = sysUserRelateRoleService.queryRolesByUserId(id);
        sysUserInfoVO.setSysRoleInfos(sysRoleInfos);
        return sysUserInfoVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteUser(String id) {
        SysUserInfo sysUserInfo = getUserById(id);
        sysUserInfo.setUpdateBy(SecurityContextHolder.getUserId());
        sysUserInfo.setDelFlag(true);
        sysUserRelateRoleService.update(null,Wrappers.<SysUserRelateRole>lambdaUpdate()
                .set(SysUserRelateRole::getDelFlag,true)
                .set(SysUserRelateRole::getUpdateBy,SecurityContextHolder.getUserId())
                .eq(SysUserRelateRole::getUserId,id));
        int result = baseMapper.updateById(sysUserInfo);
        return result != 0;
    }

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    protected SysUserInfo getUserById(String userId){
        SysUserInfo sysUserInfo = baseMapper.selectById(userId);
        if (null == sysUserInfo){
            throw new NoDataException(ServiceNameConstants.USER_SERVICE, new Object[]{userId});
        }
        return sysUserInfo;
    }
}
