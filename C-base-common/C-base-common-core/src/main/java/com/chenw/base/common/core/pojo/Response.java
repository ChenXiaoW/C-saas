package com.chenw.base.common.core.pojo;

import com.chenw.base.common.core.enums.BaseCodeEnum;

import java.io.Serializable;

/**
 * @ClassName: Response
 * @Description: 响应模型
 * @Author ChenXiaoW
 * @Date 2023/01/04 - 22:22
 */
public class Response<T> implements Serializable {

    /**
     * 成功业务码
     */
    private static final int SUCCESS_CODE= BaseCodeEnum.SUCCESS.getCode();
    /**
     * 成功提示
     */
    private static final String SUCCESS_MSG = BaseCodeEnum.SUCCESS.getMessage();
    /**
     * 失败业务码
     */
    private static final int FAIL_CODE =BaseCodeEnum.FAIL.getCode();
    /**
     * 失败提示
     */
    private static final String FAIL_MSG = BaseCodeEnum.FAIL.getMessage();

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 业务码
     */
    private Integer code;

    /**
     * 业务信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public static <T> Response<T> success(){
        return new Response<>(SUCCESS_CODE,SUCCESS_MSG,null);
    }

    public static <T> Response<T> success(T data){
        return new Response<T>(SUCCESS_CODE,SUCCESS_MSG,data);
    }

    public static <T> Response<T> fail(){
        return new Response<>(FAIL_CODE,FAIL_MSG,null);
    }

    public static <T> Response<T> fail(String message){
        return new Response<>(FAIL_CODE,message,null);
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
