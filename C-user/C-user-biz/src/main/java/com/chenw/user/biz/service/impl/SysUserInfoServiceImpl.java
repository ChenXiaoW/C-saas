package com.chenw.user.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenw.base.common.core.context.SecurityContextHolder;
import com.chenw.base.common.core.utils.PasswordEncoderUtil;
import com.chenw.user.api.dto.UserDetailDTO;
import com.chenw.user.api.entity.SysRoleInfo;
import com.chenw.user.api.entity.SysUserInfo;
import com.chenw.user.biz.dao.SysUserInfoDao;
import com.chenw.user.biz.service.SysMenuRelatePermissionService;
import com.chenw.user.biz.service.SysRoleRelateMenuService;
import com.chenw.user.biz.service.SysUserInfoService;
import com.chenw.user.biz.service.SysUserRelateRoleService;
import com.chenw.user.biz.vo.InsertOrUpdateSysUserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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


    @Override
    public String insertSysUserInfo(InsertOrUpdateSysUserInfoVO vo) {
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
        return sysUserInfo.getId();
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
}
