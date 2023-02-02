package com.chenw.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleInfo extends Model<SysRoleInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
