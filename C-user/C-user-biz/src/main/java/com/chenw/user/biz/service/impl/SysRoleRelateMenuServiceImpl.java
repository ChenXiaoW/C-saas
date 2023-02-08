package com.chenw.user.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenw.user.api.entity.SysRoleRelateMenu;
import com.chenw.user.biz.dao.SysRoleRelateMenuDao;
import com.chenw.user.biz.service.SysRoleRelateMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Service
@RequiredArgsConstructor
public class SysRoleRelateMenuServiceImpl extends ServiceImpl<SysRoleRelateMenuDao, SysRoleRelateMenu> implements SysRoleRelateMenuService {

    @Override
    public Set<String> queryMenuIdsByRoleIds(List<String> roleIds) {
        List<SysRoleRelateMenu> sysRoleRelateMenus = baseMapper.selectList(Wrappers.<SysRoleRelateMenu>lambdaQuery().in(SysRoleRelateMenu::getRoleId, roleIds));
        if (CollUtil.isEmpty(sysRoleRelateMenus)){
            return null;
        }
        return sysRoleRelateMenus.stream().map(SysRoleRelateMenu::getMenuId).collect(Collectors.toSet());
    }
}
