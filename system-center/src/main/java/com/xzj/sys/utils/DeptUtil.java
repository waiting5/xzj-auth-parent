package com.xzj.sys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzj.sys.entity.SysDept;
import com.xzj.sys.entity.vo.SysDeptVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Category com.xzj.sys.utils
 * @Description 【组织方法】
 * @Author xiaj07
 * @Since 2023/1/18 17:13
 **/
public class DeptUtil {

    /**
     * 根据传入组织List生成树结构数据
     * @param sysDeptList
     * @return
     */
    public static List<SysDeptVo> convertTree(List<SysDept> sysDeptList){
        String sysDeptStr = JSON.toJSONString(sysDeptList);
        List<SysDeptVo> sysDeptVos = JSONObject.parseArray(sysDeptStr, SysDeptVo.class);
        Map<Integer,List<SysDeptVo>>  sysDeptVoMap = new HashMap<>();
        List<Integer> integers = new ArrayList<>();
        for(SysDeptVo sysDeptVo : sysDeptVos){
            Integer parentId = sysDeptVo.getParentId();
            if(sysDeptVoMap.containsKey(parentId)){
                sysDeptVoMap.get(parentId).add(sysDeptVo);
            }else{
                List<SysDeptVo> list = new ArrayList<>();
                list.add(sysDeptVo);
                sysDeptVoMap.put(parentId,list);
                integers.add(parentId);
            }
        }
        return tree(sysDeptVoMap,integers);
    }

    /**
     * 根据顶级节点数据，遍历其子节点数据
     * @param sysDeptVoMap
     * @param integers
     */
    private static List<SysDeptVo> tree(Map<Integer,List<SysDeptVo>> sysDeptVoMap, List<Integer> integers){
        for(Integer key : integers){
            List<SysDeptVo> sysDeptVoList = sysDeptVoMap.get(key);
            convert(sysDeptVoMap,sysDeptVoList);
        }
        List<SysDeptVo> resultList = new ArrayList<>();
        for(Integer key : sysDeptVoMap.keySet()){
            resultList.addAll(sysDeptVoMap.get(key));
        }
        return resultList;
    }

    /**
     * 循环处理
     * @param sysDeptVoMap
     * @param sysDeptVoList
     */
    private static void convert(Map<Integer,List<SysDeptVo>> sysDeptVoMap,List<SysDeptVo> sysDeptVoList){
        if(null == sysDeptVoList){
            return;
        }
        for(SysDeptVo sysDeptVo : sysDeptVoList){
            List<SysDeptVo> childList = sysDeptVoMap.get(sysDeptVo.getDeptId());
            if(null != childList){
                sysDeptVo.setChildren(childList);
                sysDeptVoMap.remove(sysDeptVo.getDeptId());
                convert(sysDeptVoMap,childList);
            }
        }
    }
}
