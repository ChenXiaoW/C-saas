package com.chenw.base.common.core.exception;

/**
 * @ClassName: BussionessException
 * @Description: BussionessException
 * @Author ChenXiaoW
 * @Date 2023/02/16 - 23:10
 */
public class BussionessException extends BaseException {

    public BussionessException(String module, Integer code, Object[] args, String defaultMessage) {
        super(module, code, args, defaultMessage);
    }
}
