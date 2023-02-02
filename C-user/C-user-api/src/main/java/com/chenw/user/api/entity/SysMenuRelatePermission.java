package com.chenw.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 菜单权限关联表
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenuRelatePermission extends Model<SysMenuRelatePermission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)

    private String id;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 权限ID
     */
    private String permissionId;

    /**
     * 删除标记：0-否，1-是
     */
    private Boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
