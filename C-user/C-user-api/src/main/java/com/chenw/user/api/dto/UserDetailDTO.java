package com.chenw.user.api.dto;

import com.chenw.user.api.entity.SysPermissionInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: UserSecurityDTO
 * @Description: UserSecurityDTO
 * @Author ChenXiaoW
 * @Date 2023/02/02 - 22:41
 */
@Data
public class UserDetailDTO {

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
     * 密码
     */
    private String password;

    /**
     * 随机盐
     */
    private String salt;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人ID
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记：0-否，1-是
     */
    private Boolean delFlag;

    /**
     * 用户接口权限Code
     */
    private Set<String> userPermissions;
}
