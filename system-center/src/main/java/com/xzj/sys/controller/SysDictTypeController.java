package com.xzj.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzj.basic.base.dto.AjaxResult;
import com.xzj.sys.entity.SysDept;
import com.xzj.sys.entity.SysDictType;
import com.xzj.sys.entity.SysRole;
import com.xzj.sys.service.SysDictTypeService;
import com.xzj.sys.service.SysRoleService;
import com.xzj.sys.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类名
 *
 * @ClassName SysDictTypeController
 * 简述此类作用
 * @Description 字典类型表;(sys_dict_type)表控制层
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/15 16:19
 * 版本
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(tags = "字典类型表对象功能接口")
@RequestMapping("/system/dic/")
public class SysDictTypeController {

    @Resource
    private SysDictTypeService sysDictTypeService;

    @ApiOperation("新增字典类型数据")
    @GetMapping("tree")
    public AjaxResult tree(SysDictType sysDictType){
        QueryWrapper<SysDictType> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(sysDictType.getDictName())){
            wrapper.eq("dict_name",sysDictType.getDictName()).or().eq("dict_type",sysDictType.getDictName());
        }
        List<SysDictType> sysDictTypes = sysDictTypeService.list(wrapper);
        List<JSONObject> resultTree = CommonUtil.convertTree(JSON.toJSONString(sysDictTypes),"dictId","parentId");
        return AjaxResult.successResult().putData(resultTree);
    }

    /**
     * 新增数据
     *
     * @param sysDictType 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增字典类型数据")
    @PostMapping("addDicType")
    public AjaxResult add(@RequestBody SysDictType sysDictType){
        boolean result = sysDictTypeService.save(sysDictType);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 更新数据
     *
     * @param sysDictType 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新字典类型数据")
    @PostMapping("editDicType")
    public AjaxResult edit(@RequestBody SysDictType sysDictType){
        boolean result = sysDictTypeService.updateById(sysDictType);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除字典类型数据")
    @PostMapping("deleteTypeById")
    public AjaxResult deleteById(@RequestBody Integer dictId){
        boolean result = sysDictTypeService.removeById(dictId);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

}
