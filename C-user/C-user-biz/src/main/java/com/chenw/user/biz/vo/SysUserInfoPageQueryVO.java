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
public class SysUserInfoPageQueryVO extends PageQuery {

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户角色
     */
    private String roleId;

    /**
     * 用户状态
     */
    private Boolean state;
}
