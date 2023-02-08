package com.chenw.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenw.user.api.entity.SysMenuRelatePermission;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限关联表 服务类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
public interface SysMenuRelatePermissionService extends IService<SysMenuRelatePermission> {

    /**
     * 根据菜单ID获取权限Code列表
     * @param menuId 菜单ID
     * @return
     */
    Set<String> queryPermissionCodeByMenuId(String menuId);

    /**
     * 根据菜单ID列表获取权限CODE列表
     * @param menuIds
     * @return
     */
    Set<String> queryPermissionCodeByMenuIds(Set<String> menuIds);

}
