package com.chenw.base.common.core.exception;

/**
 * @ClassName: RemoteServiceException
 * @Description: 远程调用服务异常
 * @Author ChenXiaoW
 * @Date 2023/02/06 - 23:22
 */
public class RemoteServiceException extends BaseException {

    public RemoteServiceException(String module,Integer code,Object []args,String defaultMessage){
        super(module,code,args,defaultMessage);
    }

}
