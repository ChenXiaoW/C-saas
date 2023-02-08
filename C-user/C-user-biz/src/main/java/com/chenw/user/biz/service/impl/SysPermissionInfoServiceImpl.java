package com.chenw.user.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chenw.user.api.entity.SysPermissionInfo;
import com.chenw.user.biz.dao.SysPermissionInfoDao;
import com.chenw.user.biz.service.SysPermissionInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Service
public class SysPermissionInfoServiceImpl extends ServiceImpl<SysPermissionInfoDao, SysPermissionInfo> implements SysPermissionInfoService {

    @Override
    public Boolean hasPermission(String method, String requestUrl) {
        List<SysPermissionInfo> sysPermissionInfos = baseMapper.selectList(Wrappers.<SysPermissionInfo>lambdaQuery()
                .eq(SysPermissionInfo::getHasPermission, false).eq(SysPermissionInfo::getState, true));
        if (CollUtil.isEmpty(sysPermissionInfos)){
            return false;
        }
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (SysPermissionInfo sysPermissionInfo : sysPermissionInfos) {
            if (method.equalsIgnoreCase(sysPermissionInfo.getMethod()) && pathMatcher.match(sysPermissionInfo.getRequestUrl(),requestUrl)){
                return true;
            }
        }
        return false;
    }
}
