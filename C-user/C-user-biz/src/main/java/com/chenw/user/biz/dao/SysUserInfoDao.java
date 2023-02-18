package com.chenw.user.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenw.user.api.entity.SysUserInfo;
import com.chenw.user.biz.vo.SysUserInfoPageQueryVO;
import com.chenw.user.biz.vo.SysUserInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenw
 * @since 2023-01-07
 */
@Repository
public interface SysUserInfoDao extends BaseMapper<SysUserInfo> {

    /**
     * 分页查询用户列表
     * @param page
     * @param vo
     * @return
     */
    Page<SysUserInfoVO> queryPage(Page<SysUserInfoVO> page, @Param("params") SysUserInfoPageQueryVO vo);

}
