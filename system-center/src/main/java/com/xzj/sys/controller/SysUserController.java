package com.xzj.sys.controller;

import com.xzj.basic.base.dto.AjaxResult;
import com.xzj.sys.entity.SysUser;
import com.xzj.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类名
 *
 * @ClassName SysUserController
 * 简述此类作用
 * @Description 用户信息表对象功能接口
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/15 16:19
 * 版本
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(tags = "用户信息表对象功能接口")
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping("add")
    public AjaxResult add(@RequestBody SysUser sysUser){
        boolean result = sysUserService.save(sysUser);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 更新数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PostMapping("edit")
    public AjaxResult edit(@RequestBody SysUser sysUser){
        boolean result = sysUserService.updateById(sysUser);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @PostMapping("deleteById")
    public AjaxResult deleteById(@RequestBody Integer userId){
        boolean result = sysUserService.removeById(userId);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

}
