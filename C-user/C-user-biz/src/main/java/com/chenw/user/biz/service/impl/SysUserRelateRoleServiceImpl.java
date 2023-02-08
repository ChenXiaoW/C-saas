package com.chenw.user.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chenw.base.common.core.enums.StateEnum;
import com.chenw.user.api.entity.SysRoleInfo;
import com.chenw.user.api.entity.SysUserRelateRole;
import com.chenw.user.biz.dao.SysUserRelateRoleDao;
import com.chenw.user.biz.service.SysRoleInfoService;
import com.chenw.user.biz.service.SysUserRelateRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Service
@RequiredArgsConstructor
public class SysUserRelateRoleServiceImpl extends ServiceImpl<SysUserRelateRoleDao, SysUserRelateRole> implements SysUserRelateRoleService {


    private final SysRoleInfoService sysRoleInfoService;

    @Override
    public List<SysRoleInfo> queryRolesByUserId(String userId) {
        List<SysUserRelateRole> sysUserRelateRoles = baseMapper.selectList(Wrappers.<SysUserRelateRole>lambdaQuery()
                .eq(SysUserRelateRole::getUserId, userId));
        if (CollUtil.isEmpty(sysUserRelateRoles)){
            return null;
        }
        return sysRoleInfoService.list(Wrappers.<SysRoleInfo>lambdaQuery().eq(SysRoleInfo::getState, StateEnum.ENABLE.getState())
                .in(SysRoleInfo::getId,sysUserRelateRoles.stream().map(SysUserRelateRole::getRoleId).collect(Collectors.toList())));
    }
}
