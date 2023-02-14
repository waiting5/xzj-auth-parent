package com.xzj.sys.controller;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzj.basic.base.dto.AjaxResult;
import com.xzj.sys.common.domain.PageResponse;
import com.xzj.sys.entity.SysDept;
import com.xzj.sys.entity.SysRole;
import com.xzj.sys.entity.vo.SysRoleVo;
import com.xzj.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名
 *
 * @ClassName SysRoleController
 * 简述此类作用
 * @Description TODO
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/15 16:19
 * 版本
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(tags = "角色信息表对象功能接口")
@RequestMapping("/system/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;


    @ApiOperation("角色列表查询")
    @GetMapping("/list")
    public AjaxResult list(SysRoleVo sysRoleVo){
        PageHelper.startPage(sysRoleVo.getPage(),sysRoleVo.getPageSize());
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(sysRoleVo.getRoleName())){
            wrapper.like("role_name",sysRoleVo.getRoleName());
        }
        List<SysRole> roles = sysRoleService.list(wrapper);
        PageInfo<SysRole> pageInfo = new PageInfo<>(roles);
        PageResponse<SysRole> pageResponse = PageResponse.init(sysRoleVo.getPageSize(),sysRoleVo.getPage());
        pageResponse.putData(roles).setTotal(pageInfo.getTotal());
        return AjaxResult.successResult().putData(pageResponse);
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增/更新数据")
    @PostMapping("save")
    public AjaxResult save(@RequestBody SysRole sysRole){
        boolean result;
        if(ObjUtil.isNotEmpty(sysRole.getRoleId())){
            result = sysRoleService.updateById(sysRole);
        }else{
            result = sysRoleService.save(sysRole);
        }
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param sysRole 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @PostMapping("deleteById")
    public AjaxResult deleteById(@RequestBody SysRole sysRole){
        boolean result = sysRoleService.removeById(sysRole.getRoleId());
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    @ApiOperation("批量删除角色信息")
    @PostMapping("batchDel")
    public AjaxResult batchDel(@RequestBody List<SysRole> sysRoles){
        List<Integer> ids = new ArrayList<>();
        for(SysRole sysRole:sysRoles){
            ids.add(sysRole.getRoleId());
        }
        boolean result = sysRoleService.removeByIds(ids);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

}
