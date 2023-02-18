package com.chenw.user.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chenw.user.api.entity.SysRoleInfo;
import com.chenw.user.biz.vo.InsertOrUpdateSysRoleInfoVO;
import com.chenw.user.biz.vo.SysRoleInfoPageQueryVO;
import com.chenw.user.biz.vo.SysRoleInfoVO;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
public interface SysRoleInfoService extends IService<SysRoleInfo> {

    /**
     * 新增角色
     *
     * @param vo
     * @return
     */
    String insertRole(InsertOrUpdateSysRoleInfoVO vo);

    /**
     * 更新角色
     *
     * @param id
     * @param vo
     * @return
     */
    String updateRole(String id, InsertOrUpdateSysRoleInfoVO vo);

    /**
     * 查询角色详情
     *
     * @param id
     * @return
     */
    SysRoleInfoVO queryDetail(String id);

    /**
     * 分页查询角色
     *
     * @param vo
     * @return
     */
    Page<SysRoleInfoVO> page(SysRoleInfoPageQueryVO vo);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    Boolean deleteRole(String id);

}
