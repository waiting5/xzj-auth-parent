package com.xzj.sys.controller;

import com.xzj.basic.base.dto.AjaxResult;
import com.xzj.sys.entity.SysDictData;
import com.xzj.sys.entity.SysDictType;
import com.xzj.sys.service.SysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类名 @ClassName SysDictTypeController
 * 简述此类作用 @Description 字典类型表;(sys_dict_type)表控制层
 * 作者 @Author xiajun
 * 创建时间 @Date 2023/1/15 16:19
 * 版本 ersion 1.0
 **/
@Slf4j
@Api(tags = "字典数据表对象功能接口")
@RestController
@RequestMapping("/sysDictData")
public class SysDictDataController {

    @Resource
    private SysDictDataService sysDictDataService;

    /**
     * 新增数据
     *
     * @param sysDictData 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping("add")
    public AjaxResult add(@RequestBody SysDictData sysDictData){
        boolean result = sysDictDataService.save(sysDictData);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 更新数据
     *
     * @param sysDictData 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PostMapping("edit")
    public AjaxResult edit(@RequestBody SysDictData sysDictData){
        boolean result = sysDictDataService.updateById(sysDictData);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param dictCode 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @PostMapping("deleteById")
    public AjaxResult deleteById(@RequestBody Integer dictCode){
        boolean result = sysDictDataService.removeById(dictCode);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

}
