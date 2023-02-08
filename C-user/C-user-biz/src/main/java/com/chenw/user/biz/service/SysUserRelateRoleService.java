package com.chenw.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenw.user.api.entity.SysRoleInfo;
import com.chenw.user.api.entity.SysUserRelateRole;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
public interface SysUserRelateRoleService extends IService<SysUserRelateRole> {

    /**
     * 根据用户ID查询关联的角色
     * @param userId
     * @return
     */
    List<SysRoleInfo> queryRolesByUserId(String userId);

}
