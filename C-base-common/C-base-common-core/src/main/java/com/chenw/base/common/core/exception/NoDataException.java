package com.chenw.base.common.core.exception;

import com.chenw.base.common.core.enums.BaseCodeEnum;

/**
 * @ClassName: NoDataEception
 * @Description: NoDataEception
 * @Author ChenXiaoW
 * @Date 2023/02/08 - 22:59
 */
public class NoDataException extends BaseException {

    public NoDataException(String module, Object[] args) {
        super(module, BaseCodeEnum.NO_DATA.getCode(), args, BaseCodeEnum.NO_DATA.getMessage());
    }
}
