package com.chenw.base.common.core.exception.auth;

import com.chenw.base.common.core.enums.BaseCodeEnum;
import com.chenw.base.common.core.exception.BaseException;

/**
 * @ClassName: AccessdeniedException
 * @Description: AccessdeniedException
 * @Author ChenXiaoW
 * @Date 2023/01/04 - 22:52
 */
public class AccessdeniedException extends BaseException {

    private static final Integer CODE = BaseCodeEnum.ACCESSDENIED.getCode();

    private static final String MESSAGE = BaseCodeEnum.ACCESSDENIED.getMessage();

    public AccessdeniedException() {
        super("auth", CODE, null, MESSAGE);
    }

    public AccessdeniedException(String defaultMessage) {
        super("auth", CODE, null, defaultMessage);
    }
}
