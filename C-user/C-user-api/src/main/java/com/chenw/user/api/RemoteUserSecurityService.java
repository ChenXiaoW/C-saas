package com.chenw.user.api;

import com.chenw.base.common.core.constant.ServiceNameConstants;
import com.chenw.base.common.core.pojo.Response;
import com.chenw.user.api.dto.UserDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "remoteUserSecurityService",value = ServiceNameConstants.USER_SERVICE)
public interface RemoteUserSecurityService {

    final String PERFIX = "/userSecurity";

    /**
     * 查询用户详细信息用于鉴权
     * @param userId
     * @return
     */
    @GetMapping(PERFIX+"/queryUserDetailToSecurity")
    public Response<UserDetailDTO> queryUserDetailToSecurity(@RequestParam("userId") String userId);

    /**
     * 判断是否需要权限
     * @param method
     * @param requestUrl
     * @return
     */
    @GetMapping(PERFIX+"/hasPermission")
    public Response<Boolean> hasPermission(@RequestParam("method")String method,@RequestParam("requestUrl")String requestUrl);
}
