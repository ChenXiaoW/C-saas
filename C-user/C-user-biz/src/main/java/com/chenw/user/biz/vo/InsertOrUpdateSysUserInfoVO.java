package com.chenw.user.biz.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: InsertOrUpdateSysUserInfoVO
 * @Description: 新增/更新用户信息
 * @Author ChenXiaoW
 * @Date 2023/01/08 - 20:17
 */
@Data
public class InsertOrUpdateSysUserInfoVO {

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态：0-禁用，1-启用
     */
    private Boolean state;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

}
