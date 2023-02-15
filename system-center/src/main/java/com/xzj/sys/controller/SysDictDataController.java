package com.xzj.sys.controller;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzj.basic.base.dto.AjaxResult;
import com.xzj.sys.common.domain.PageResponse;
import com.xzj.sys.entity.SysDictData;
import com.xzj.sys.entity.vo.SysDicDataVo;
import com.xzj.sys.service.SysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 类名 @ClassName SysDictTypeController
 * 简述此类作用 @Description 字典类型表;(sys_dict_type)表控制层
 * 作者 @Author xiajun
 * 创建时间 @Date 2023/1/15 16:19
 * 版本 ersion 1.0
 **/
@Slf4j
@Api(tags = "字典数据表对象功能接口")
@RestController
@RequestMapping("/system/dic/data/")
public class SysDictDataController {

    @Resource
    private SysDictDataService sysDictDataService;

    @ApiOperation("字典列表查询")
    @GetMapping("list")
    public AjaxResult list(SysDicDataVo sysDicDataVo){
        PageHelper.startPage(sysDicDataVo.getPage(),sysDicDataVo.getPageSize());
        QueryWrapper<SysDictData> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type",sysDicDataVo.getDictType());
        List<SysDictData> dataList = sysDictDataService.list(wrapper);
        PageInfo<SysDictData> pageInfo = new PageInfo<>(dataList);
        PageResponse<SysDictData> pageResponse = PageResponse.init(pageInfo.getPageSize(),pageInfo.getPageNum());
        pageResponse.putData(dataList).setTotal(pageInfo.getTotal());
        return AjaxResult.successResult().putData(pageResponse);
    }

    /**
     * 新增数据
     *
     * @param sysDictData 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping("add")
    public AjaxResult add(@RequestBody SysDictData sysDictData){
        boolean result;
        if(ObjUtil.isNotEmpty(sysDictData.getDictCode())){
            result = sysDictDataService.updateById(sysDictData);
        }else{
            result = sysDictDataService.save(sysDictData);
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
     * @param sysDictData 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @PostMapping("deleteById")
    public AjaxResult deleteById(@RequestBody SysDictData sysDictData){
        boolean result = sysDictDataService.removeById(sysDictData.getDictCode());
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 批量删除字典信息
     * @param dataList
     * @return
     */
    @ApiOperation("批量删除字典信息")
    @PostMapping("batchDel")
    public AjaxResult batchDel(@RequestBody List<SysDictData> dataList){
        List<Integer> ids = new ArrayList<>();
        for(SysDictData sysDictData:dataList){
            ids.add(sysDictData.getDictCode());
        }
        boolean result = sysDictDataService.removeByIds(ids);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }
}
