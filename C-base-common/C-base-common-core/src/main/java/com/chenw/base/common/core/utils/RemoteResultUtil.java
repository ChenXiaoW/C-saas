package com.chenw.base.common.core.utils;

import com.chenw.base.common.core.exception.RemoteServiceException;
import com.chenw.base.common.core.pojo.Response;

/**
 * @ClassName: RemoteResultUtil
 * @Description: RemoteResultUtil
 * @Author ChenXiaoW
 * @Date 2023/02/06 - 23:18
 */
public class RemoteResultUtil {


    /**
     * 获取结果
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> T getResult(Response<T> response) {
        if (Response.fail().getCode().equals(response.getCode())) {
            throw new RemoteServiceException(response.getServiceName(), response.getCode(), null, response.getMessage());
        }
        return response.getData();
    }
}
