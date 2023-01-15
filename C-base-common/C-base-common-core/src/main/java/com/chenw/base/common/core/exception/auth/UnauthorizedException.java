package com.chenw.base.common.core.exception.auth;

import com.chenw.base.common.core.enums.BaseCodeEnum;
import com.chenw.base.common.core.exception.BaseException;

/**
 * @ClassName: UnauthorizedException
 * @Description: 未认证异常
 * @Author ChenXiaoW
 * @Date 2023/01/04 - 22:38
 */
public class UnauthorizedException extends BaseException {

    private static final Integer CODE = BaseCodeEnum.UNAUTHORIZED.getCode();
    private static final String MESSAGE = BaseCodeEnum.UNAUTHORIZED.getMessage();

    public UnauthorizedException( ) {
        super("auth", CODE, null, MESSAGE);
    }

    public UnauthorizedException( String defaultMessage) {
        super("auth", CODE, null, defaultMessage);
    }
}
