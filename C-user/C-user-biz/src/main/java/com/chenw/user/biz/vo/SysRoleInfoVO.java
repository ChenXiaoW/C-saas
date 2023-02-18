package com.chenw.user.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: SysRoleInfoVO
 * @Description: SysRoleInfoVO
 * @Author ChenXiaoW
 * @Date 2023/02/16 - 22:47
 */
@Data
public class SysRoleInfoVO {

    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色CODE
     */
    private String roleCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：0-禁用，1-启用
     */
    private Boolean state;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 创建人姓名
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
}
