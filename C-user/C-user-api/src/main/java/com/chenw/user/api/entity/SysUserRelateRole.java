package com.chenw.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRelateRole extends Model<SysUserRelateRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)

    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

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
    @TableLogic
    private Boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
