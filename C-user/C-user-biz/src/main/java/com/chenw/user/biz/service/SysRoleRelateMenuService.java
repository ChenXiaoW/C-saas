package com.chenw.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenw.user.api.entity.SysRoleRelateMenu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
public interface SysRoleRelateMenuService extends IService<SysRoleRelateMenu> {

    /**
     * 查询角色关联的菜单ID
     * @param roleIds - 菜单ID列表
     * @return
     */
    Set<String> queryMenuIdsByRoleIds(List<String> roleIds);

}
