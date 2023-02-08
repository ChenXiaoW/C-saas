package com.chenw.user.biz.remote;

import com.chenw.base.common.core.pojo.Response;
import com.chenw.user.api.dto.UserDetailDTO;
import com.chenw.user.api.entity.SysPermissionInfo;
import com.chenw.user.biz.service.SysPermissionInfoService;
import com.chenw.user.biz.service.SysUserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserSecurityController
 * @Description: UserSecurityController
 * @Author ChenXiaoW
 * @Date 2023/02/07 - 22:01
 */

@RestController
@RequestMapping("/userSecurity")
public class UserSecurityController {

    @Resource
    private SysUserInfoService sysUserInfoService;

    @Resource
    private SysPermissionInfoService sysPermissionInfoService;

    /**
     * 查询用户详情用于鉴权
     * @param userId - 用户ID
     * @return
     */
    @GetMapping("/queryUserDetailToSecurity")
    public Response<UserDetailDTO> queryUserDetailToSecurity(@RequestParam("userId") String userId){
        return Response.success(sysUserInfoService.queryUserDetailToSecurity(userId));
    }

    /**
     * 判断资源是否需要权限
     * @param method - 请求方式
     * @param requestUrl - 请求路径
     * @return
     */
    @GetMapping("/hasPermission")
    public Response<Boolean> hasPermission(@RequestParam("method")String method,@RequestParam("requestUrl")String requestUrl){
        return Response.success(sysPermissionInfoService.hasPermission(method,requestUrl));
    }
}
