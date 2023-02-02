package com.chenw.user.api.feign;

import com.chenw.base.common.core.constant.ServiceNameConstants;
import com.chenw.base.common.core.pojo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(contextId = "remoteUserSecurityService",value = ServiceNameConstants.USER_SERVICE)
public interface RemoteUserSecurityService {

    /**
     * 查询用户详细信息用于鉴权
     * @param userId
     * @return
     */
    @PostMapping("/")
    public Response queryUserDetailToSecurity(String userId);
}
