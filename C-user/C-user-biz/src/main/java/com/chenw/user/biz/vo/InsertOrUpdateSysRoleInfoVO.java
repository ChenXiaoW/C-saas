package com.chenw.user.biz.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: InsertOrUpdateSysRoleInfoVO
 * @Description: InsertOrUpdateSysRoleInfoVO
 * @Author ChenXiaoW
 * @Date 2023/02/16 - 22:40
 */
@Data
public class InsertOrUpdateSysRoleInfoVO {

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
     * 角色菜单权限
     */
    private List<String> menuIds;
}
