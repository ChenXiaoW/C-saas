package com.chenw.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenw.user.api.entity.SysPermissionInfo;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
public interface SysPermissionInfoService extends IService<SysPermissionInfo> {


    /**
     * 判断资源是否需要权限
     * @param method - 请求方式
     * @param requestUrl - 请求路径
     * @return
     */
    Boolean hasPermission(String method, String requestUrl);

}
