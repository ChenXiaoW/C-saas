package com.chenw.base.common.core.enums;



/**
 * @author chenw
 *
 * 基础业务码枚举
 */

public enum BaseCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200,"SUCCESS"),
    /**
     * 失败
     */
    FAIL(500,"FAIL"),

    /**
     * 未认证
     */
    UNAUTHORIZED(401, "not login or token expired"),
    /**
     * 访问被拒绝
     */
    ACCESSDENIED(403, "not permission"),;

    private Integer code;

    private String message;

    BaseCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
