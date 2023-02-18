package com.chenw.user.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenw.base.common.core.pojo.Response;
import com.chenw.user.biz.service.SysUserInfoService;
import com.chenw.user.biz.vo.InsertOrUpdateSysUserInfoVO;
import com.chenw.user.biz.vo.SysUserInfoPageQueryVO;
import com.chenw.user.biz.vo.SysUserInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: SysUserInfoController
 * @Description: 用户管理
 * @Author ChenXiaoW
 * @Date 2023/01/07 - 23:12
 */
@RestController
@RequestMapping("/sysUserInfo")
public class SysUserInfoController {

    @Resource
    private SysUserInfoService sysUserInfoService;
    /**
     * 新增用户
     */
    @PostMapping()
    public Response<String> insertUser(@RequestBody InsertOrUpdateSysUserInfoVO vo){
        return Response.success(sysUserInfoService.insertSysUserInfo(vo));
    }

    /**
     * 更新用户信息
     * @param id - 用户ID
     * @param vo
     * @return
     */
    @PutMapping("/{id}")
    public Response<String> updateSysUserInfo(@PathVariable("id")String id ,@RequestBody InsertOrUpdateSysUserInfoVO vo){
        return Response.success(sysUserInfoService.updateSysUserInfo(id,vo));
    }

    /**
     * 分页查询用户列表
     * @param vo
     * @return
     */
    @GetMapping("/page")
    public Response<Page<SysUserInfoVO>> queryPage(SysUserInfoPageQueryVO vo){
        return Response.success(sysUserInfoService.queryPage(vo));
    }

    /**
     * 根据用户ID查询详情
     * @param id - 用户ID
     * @return
     */
    @GetMapping("/{id}")
    public Response<SysUserInfoVO> queryDetail(@PathVariable("id")String id ){
        return Response.success(sysUserInfoService.queryDetail(id));
    }

    /**
     * 删除用户
     * @param id - 用户ID
     * @return
     */
    @DeleteMapping("/{id}")
    public Response<Boolean> deleteUser(@PathVariable("id")String id ){
        return Response.success(sysUserInfoService.deleteUser(id));
    }

}
