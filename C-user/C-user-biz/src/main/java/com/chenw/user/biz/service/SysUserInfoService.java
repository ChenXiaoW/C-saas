package com.chenw.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenw.user.api.dto.UserDetailDTO;
import com.chenw.user.api.entity.SysUserInfo;
import com.chenw.user.biz.vo.InsertOrUpdateSysUserInfoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenw
 * @since 2023-01-07
 */
public interface SysUserInfoService extends IService<SysUserInfo> {

    /**
     * 新增用户
     * @param vo
     * @return
     */
    String insertSysUserInfo(InsertOrUpdateSysUserInfoVO vo);


    /**
     * 查询用户详情用于鉴权
     * @param userId 用户ID
     * @return
     */
    UserDetailDTO queryUserDetailToSecurity(String userId);
}
