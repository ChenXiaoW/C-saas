package com.chenw.user.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chenw.user.api.dto.UserDetailDTO;
import com.chenw.user.api.entity.SysUserInfo;
import com.chenw.user.biz.vo.InsertOrUpdateSysUserInfoVO;
import com.chenw.user.biz.vo.SysUserInfoPageQueryVO;
import com.chenw.user.biz.vo.SysUserInfoVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenw
 * @since 2023-01-07
 */
public interface SysUserInfoService extends IService<SysUserInfo> {

    /**
     * 新增用户
     *
     * @param vo - 新增信息
     * @return
     */
    String insertSysUserInfo(InsertOrUpdateSysUserInfoVO vo);

    /**
     * 更新用户
     *
     * @param userId - 用户ID
     * @param vo     - 更新信息
     * @return
     */
    String updateSysUserInfo(String userId, InsertOrUpdateSysUserInfoVO vo);


    /**
     * 查询用户详情用于鉴权
     *
     * @param userId 用户ID
     * @return
     */
    UserDetailDTO queryUserDetailToSecurity(String userId);

    /**
     * 分页查询用户
     *
     * @param vo
     * @return
     */
    Page<SysUserInfoVO> queryPage(SysUserInfoPageQueryVO vo);

    /**
     * 查询用户详情
     *
     * @param id - 用户ID
     * @return
     */
    SysUserInfoVO queryDetail(String id);

    /**
     * 删除用户
     *
     * @param id - 用户ID
     * @return
     */
    Boolean deleteUser(String id);
}
