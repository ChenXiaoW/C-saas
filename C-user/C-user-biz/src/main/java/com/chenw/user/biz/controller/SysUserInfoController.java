package com.chenw.user.biz.controller;

import com.chenw.base.common.core.pojo.Response;
import com.chenw.user.biz.service.SysUserInfoService;
import com.chenw.user.biz.vo.InsertOrUpdateSysUserInfoVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
