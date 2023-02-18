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
 * 菜单表
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenuInfo extends Model<SysMenuInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)

    private String id;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 使用场景:0-平台，1-租户，2-小程序
     */
    private Boolean scene;

    /**
     * 菜单CODE
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 页面路径
     */
    private String pageUrl;

    /**
     * 菜单类型：0-主菜单,1-页面,2-按钮
     */
    private Boolean menuType;

    /**
     * 菜单ICON
     */
    private String menuIcon;

    /**
     * 菜单顺序
     */
    private Integer menuOrder;

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
    @TableLogic
    private Boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
