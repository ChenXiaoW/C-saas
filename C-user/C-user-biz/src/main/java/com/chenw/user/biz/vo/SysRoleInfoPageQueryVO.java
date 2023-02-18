package com.chenw.user.biz.vo;

import com.chenw.base.common.core.pojo.PageQuery;
import lombok.Data;

/**
 * @ClassName: SysUserInfoListQueryVO
 * @Description: SysUserInfoListQueryVO
 * @Author ChenXiaoW
 * @Date 2023/02/15 - 22:14
 */
@Data
public class SysRoleInfoPageQueryVO extends PageQuery {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态
     */
    private Boolean state;
}
