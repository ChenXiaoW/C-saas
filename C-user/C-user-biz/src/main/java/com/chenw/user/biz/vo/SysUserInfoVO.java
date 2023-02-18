package com.chenw.user.biz.vo;

import com.chenw.user.api.entity.SysRoleInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: SysUserInfoPageVO
 * @Description: SysUserInfoPageVO
 * @Author ChenXiaoW
 * @Date 2023/02/15 - 22:13
 */
@Data
public class SysUserInfoVO {

    /**
     * 主键ID
     */
    private String id;

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
     * 创建人ID
     */
    private String createBy;

    /**
     * 创建人名称
     */
    private String createByName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人ID
     */
    private String updateBy;

    /**
     * 更新人姓名
     */
    private String updateByName;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 用户角色
     */
    private List<SysRoleInfo> sysRoleInfos;

}
