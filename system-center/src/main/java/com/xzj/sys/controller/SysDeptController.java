package com.xzj.sys.controller;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzj.basic.base.dto.AjaxResult;
import com.xzj.sys.entity.SysDept;
import com.xzj.sys.entity.vo.SysDeptVo;
import com.xzj.sys.service.SysDeptService;
import com.xzj.sys.utils.CommonUtil;
import com.xzj.sys.utils.DeptUtil;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名
 *
 * @ClassName SysDeptController
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
@Api(tags = "部门表对象功能接口")
@RequestMapping("/system/dept")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @ApiOperation("查询组织树")
    @GetMapping("list")
    public AjaxResult selectTree(Integer filterId,String deptName){
        QueryWrapper<SysDept> wrapper = new QueryWrapper<>();
        Map<String,String> wrapperMap = new HashMap<>();
        wrapperMap.put("status","0");
        wrapperMap.put("del_flag","0");
        wrapper.allEq(wrapperMap);
        if(null != filterId){
            wrapper.notIn("dept_id",filterId);
        }
        if(StrUtil.isNotEmpty(deptName)){
            wrapper.like("dept_name",deptName);
        }
        List<SysDept> sysDeptList = sysDeptService.list(wrapper);
        // List<SysDeptVo> sysDeptVoList = DeptUtil.convertTree(sysDeptList);
        List<JSONObject> jsonObjects = CommonUtil.convertTree(JSONObject.toJSONString(sysDeptList),"deptId","parentId");
        return AjaxResult.successResult().putData(jsonObjects);
    }

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping("add")
    public AjaxResult add(@RequestBody SysDept sysDept){
        if(ObjUtil.isEmpty(sysDept.getDeptId())){
            sysDept.setCreatedTime(new Date());
            sysDept.setCreatedBy("xiajun");
            boolean result = sysDeptService.save(sysDept);
            if(result){
                return AjaxResult.successResult();
            }else{
                return AjaxResult.failResult();
            }
        }else{
            return edit(sysDept);
        }
    }

    /**
     * 更新数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PostMapping("edit")
    public AjaxResult edit(@RequestBody SysDept sysDept){
        sysDept.setUpdatedTime(new Date());
        sysDept.setUpdatedBy("xiajun");
        boolean result = sysDeptService.updateById(sysDept);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param sysDept 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @PostMapping("deleteById")
    public AjaxResult deleteById(@RequestBody SysDept sysDept){
        boolean result = sysDeptService.removeById(sysDept.getDeptId());
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }

    @ApiOperation("批量删除")
    @PostMapping("batchDel")
    public AjaxResult batchDel(@RequestBody List<SysDept> sysDeptList){
        List<Integer> ids = new ArrayList<>();
        for(SysDept sysDept:sysDeptList){
            ids.add(sysDept.getDeptId());
        }
        boolean result = sysDeptService.removeByIds(ids);
        if(result){
            return AjaxResult.successResult();
        }else{
            return AjaxResult.failResult();
        }
    }
}
