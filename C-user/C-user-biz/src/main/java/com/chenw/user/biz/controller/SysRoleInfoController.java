package com.chenw.user.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenw.base.common.core.pojo.Response;
import com.chenw.user.biz.service.SysRoleInfoService;
import com.chenw.user.biz.vo.InsertOrUpdateSysRoleInfoVO;
import com.chenw.user.biz.vo.SysRoleInfoPageQueryVO;
import com.chenw.user.biz.vo.SysRoleInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: SysRoleInfoController
 * @Description: SysRoleInfoController
 * @Author ChenXiaoW
 * @Date 2023/02/16 - 22:39
 */
@RestController
@RequestMapping("/sysRoleInfo")
public class SysRoleInfoController {

    @Resource
    private SysRoleInfoService sysRoleInfoService;


    /**
     * 新增角色
     * @param vo
     * @return
     */
    @PostMapping
    public Response<String> insertRole(@RequestBody InsertOrUpdateSysRoleInfoVO vo){
        return Response.success(sysRoleInfoService.insertRole(vo));
    }


    /**
     * 更新角色
     * @param vo
     * @return
     */
    @PutMapping("/{id}")
    public Response<String> updateRole(@PathVariable("id")String id,@RequestBody InsertOrUpdateSysRoleInfoVO vo){
        return Response.success(sysRoleInfoService.updateRole(id,vo));
    }


    /**
     * 查询角色
     * @param id - 角色ID
     * @return
     */
    @GetMapping("/{id}")
    public Response<SysRoleInfoVO> queryDetail(@PathVariable("id")String id){
        return Response.success(sysRoleInfoService.queryDetail(id));
    }

    /**
     * 分页查询角色
     * @param vo
     * @return
     */
    @GetMapping("/page")
    public Response<Page<SysRoleInfoVO>> page(SysRoleInfoPageQueryVO vo){
        return Response.success(sysRoleInfoService.page(vo));
    }


    /**
     * 删除角色
     * @param id - 角色ID
     * @return
     */
    @DeleteMapping("/{id}")
    public Response<Boolean> deleteRole(@PathVariable("id")String id){
        return Response.success(sysRoleInfoService.deleteRole(id));
    }
}
