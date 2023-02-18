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
import com.chenw.user.api.entity.*;
import com.chenw.user.biz.dao.SysRoleInfoDao;
import com.chenw.user.biz.service.*;
import com.chenw.user.biz.vo.InsertOrUpdateSysRoleInfoVO;
import com.chenw.user.biz.vo.SysRoleInfoPageQueryVO;
import com.chenw.user.biz.vo.SysRoleInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Optional;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Service
@RequiredArgsConstructor
public class SysRoleInfoServiceImpl extends ServiceImpl<SysRoleInfoDao, SysRoleInfo> implements SysRoleInfoService {

    private final SysRoleRelateMenuService sysRoleRelateMenuService;

    private final SysUserRelateRoleService sysUserRelateRoleService;

    private final SysUserInfoService sysUserInfoService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertRole(InsertOrUpdateSysRoleInfoVO vo) {
        String userId = SecurityContextHolder.getUserId();
        Integer count = baseMapper.selectCount(Wrappers.<SysRoleInfo>lambdaQuery().eq(SysRoleInfo::getRoleName, vo.getRoleName()));
        if (count != 0) {
            throw new BussionessException(ServiceNameConstants.USER_SERVICE, BaseCodeEnum.DATA_EXISTS.getCode(), new String[]{vo.getRoleName()}, BaseCodeEnum.DATA_EXISTS.getMessage());
        }
        SysRoleInfo sysRoleInfo = new SysRoleInfo();
        BeanUtils.copyProperties(vo, sysRoleInfo);
        sysRoleInfo.setCreateBy(userId);
        baseMapper.insert(sysRoleInfo);
        Optional.of(vo.getMenuIds()).orElse(new ArrayList<>()).forEach(item -> {
            sysRoleRelateMenuService.save(new SysRoleRelateMenu(sysRoleInfo.getId(), item, userId));
        });
        return sysRoleInfo.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateRole(String id, InsertOrUpdateSysRoleInfoVO vo) {
        String userId = SecurityContextHolder.getUserId();
        Integer count = baseMapper.selectCount(Wrappers.<SysRoleInfo>lambdaQuery()
                .eq(SysRoleInfo::getRoleName, vo.getRoleName()).ne(SysRoleInfo::getId, id));
        if (count != 0) {
            throw new BussionessException(ServiceNameConstants.USER_SERVICE, BaseCodeEnum.DATA_EXISTS.getCode(), new String[]{vo.getRoleName()}, BaseCodeEnum.DATA_EXISTS.getMessage());
        }
        getRoleById(id);
        baseMapper.update(null, Wrappers.<SysRoleInfo>lambdaUpdate()
                .set(SysRoleInfo::getState, vo.getState())
                .set(SysRoleInfo::getRoleName, vo.getRoleName())
                .set(SysRoleInfo::getRemark, vo.getRemark())
                .set(SysRoleInfo::getUpdateBy, userId)
                .eq(SysRoleInfo::getId, id));
        sysRoleRelateMenuService.update(Wrappers.<SysRoleRelateMenu>lambdaUpdate()
                .set(SysRoleRelateMenu::getDelFlag, true)
                .set(SysRoleRelateMenu::getUpdateBy, userId)
                .eq(SysRoleRelateMenu::getRoleId, id));
        Optional.of(vo.getMenuIds()).orElse(new ArrayList<>()).forEach(item -> {
            sysRoleRelateMenuService.save(new SysRoleRelateMenu(id, item, userId));
        });
        return id;
    }

    @Override
    public SysRoleInfoVO queryDetail(String id) {
        SysRoleInfo sysRoleInfo = getRoleById(id);

        SysRoleInfoVO vo = new SysRoleInfoVO();
        BeanUtils.copyProperties(sysRoleInfo, vo);
        SysUserInfo createUserBy = sysUserInfoService.getById(vo.getCreateBy());
        if (null != createUserBy) {
            vo.setCreateByName(createUserBy.getUserName());
        }
        return vo;
    }

    @Override
    public Page<SysRoleInfoVO> page(SysRoleInfoPageQueryVO vo) {
        Page<SysRoleInfoVO> page = baseMapper.page(new Page<SysRoleInfoVO>(vo.getCurrent(), vo.getSize()),
                vo.getRoleName(), vo.getState());
        if (CollUtil.isNotEmpty(page.getRecords())) {
            page.getRecords().forEach(item -> {
                SysUserInfo createUserBy = sysUserInfoService.getById(item.getCreateBy());
                if (null != createUserBy) {
                    item.setCreateByName(createUserBy.getUserName());
                }
            });
        }
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteRole(String id) {
        String userId = SecurityContextHolder.getUserId();
        getRoleById(id);
        sysRoleRelateMenuService.update(null, Wrappers.<SysRoleRelateMenu>lambdaUpdate()
                .set(SysRoleRelateMenu::getDelFlag, true)
                .set(SysRoleRelateMenu::getUpdateBy, userId)
                .eq(SysRoleRelateMenu::getRoleId, id));
        sysUserRelateRoleService.update(null, Wrappers.<SysUserRelateRole>lambdaUpdate()
                .set(SysUserRelateRole::getUpdateBy, userId)
                .set(SysUserRelateRole::getDelFlag, true)
                .eq(SysUserRelateRole::getRoleId, id));
        int result = baseMapper.update(null, Wrappers.<SysRoleInfo>lambdaUpdate().set(SysRoleInfo::getDelFlag, true)
                .set(SysRoleInfo::getUpdateBy, userId).eq(SysRoleInfo::getId, id));
        return result != 0;
    }

    /**
     * 根据ID获取角色信息
     *
     * @param id
     * @return
     */
    protected SysRoleInfo getRoleById(String id) {
        SysRoleInfo sysRoleInfo = baseMapper.selectById(id);
        if (null == sysRoleInfo) {
            throw new NoDataException(ServiceNameConstants.USER_SERVICE, new String[]{id});
        }
        return sysRoleInfo;
    }
}
