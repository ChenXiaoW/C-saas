package com.chenw.base.common.core.exception;

/**
 * @ClassName: BaseException
 * @Description: 基础异常
 * @Author ChenXiaoW
 * @Date 2023/01/03 - 23:12
 */
public class BaseException extends RuntimeException {

    /**
     * 模块
     */
    private String module;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码对应的参数
     */
    private Object [] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module,Integer code,Object []args,String defaultMessage){
        super(defaultMessage);
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
