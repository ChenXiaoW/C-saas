package com.chenw.user.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenw.base.common.core.enums.StateEnum;
import com.chenw.user.api.entity.SysMenuRelatePermission;
import com.chenw.user.api.entity.SysPermissionInfo;
import com.chenw.user.biz.dao.SysMenuRelatePermissionDao;
import com.chenw.user.biz.service.SysMenuRelatePermissionService;
import com.chenw.user.biz.service.SysPermissionInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限关联表 服务实现类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Service
@RequiredArgsConstructor
public class SysMenuRelatePermissionServiceImpl extends ServiceImpl<SysMenuRelatePermissionDao, SysMenuRelatePermission> implements SysMenuRelatePermissionService {

    private final SysPermissionInfoService sysPermissionInfoService;

    @Override
    public Set<String> queryPermissionCodeByMenuId(String menuId) {
        List<SysMenuRelatePermission> sysMenuRelatePermissions = baseMapper.selectList(Wrappers.<SysMenuRelatePermission>lambdaQuery()
                .eq(SysMenuRelatePermission::getMenuId, menuId));
        if (CollUtil.isEmpty(sysMenuRelatePermissions)){
            return null;
        }
        Set<String> permissionIds = sysMenuRelatePermissions.stream().map(SysMenuRelatePermission::getPermissionId).collect(Collectors.toSet());
        List<SysPermissionInfo> sysPermissionInfos = sysPermissionInfoService.list(Wrappers.<SysPermissionInfo>lambdaQuery().in(SysPermissionInfo::getId, permissionIds)
                .eq(SysPermissionInfo::getState, StateEnum.ENABLE.getState()));
        return sysPermissionInfos.stream().map(SysPermissionInfo::getPermissionCode).collect(Collectors.toSet());
    }

    @Override
    public Set<String> queryPermissionCodeByMenuIds(Set<String> menuIds) {
        List<SysMenuRelatePermission> sysMenuRelatePermissions = baseMapper.selectList(Wrappers.<SysMenuRelatePermission>lambdaQuery()
                .in(SysMenuRelatePermission::getMenuId, menuIds));
        if (CollUtil.isEmpty(sysMenuRelatePermissions)){
            return null;
        }
        Set<String> permissionIds = sysMenuRelatePermissions.stream().map(SysMenuRelatePermission::getPermissionId).collect(Collectors.toSet());
        List<SysPermissionInfo> sysPermissionInfos = sysPermissionInfoService.list(Wrappers.<SysPermissionInfo>lambdaQuery().in(SysPermissionInfo::getId, permissionIds)
                .eq(SysPermissionInfo::getState, StateEnum.ENABLE.getState()));
        return sysPermissionInfos.stream().map(SysPermissionInfo::getPermissionCode).collect(Collectors.toSet());
    }
}
