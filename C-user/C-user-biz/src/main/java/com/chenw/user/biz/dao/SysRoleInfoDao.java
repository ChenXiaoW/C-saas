package com.chenw.user.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenw.user.api.entity.SysRoleInfo;
import com.chenw.user.biz.vo.SysRoleInfoVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author chenw
 * @since 2023-02-02
 */
public interface SysRoleInfoDao extends BaseMapper<SysRoleInfo> {

    /**
     * 分页查询角色
     * @param page
     * @param roleName
     * @param state
     * @return
     */
    Page<SysRoleInfoVO> page(Page<SysRoleInfoVO> page, @Param("roleName")String roleName,@Param("state")Boolean state);

}
