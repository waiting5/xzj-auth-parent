package com.xzj.sys.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xzj.sys.entity.vo.SysDeptVo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Category com.xzj.sys.utils
 * @Description 【功能描述】
 * @Author xiaj07
 * @Since 2023/1/28 8:49
 **/
@Slf4j
public class CommonUtil {

    /**
     * 根据传入组织List生成树结构数据
     * @param treeStr
     * @param id
     * @param parentId
     * @return
     */
    public static List<JSONObject> convertTree(String treeStr,String id,String parentId){
        JSONArray treeSourceArray = JSONArray.parseArray(treeStr);
        Map<Integer,List<JSONObject>> convertTreeMap = new HashMap<>();
        List<Integer> parentIds = new ArrayList<>();
        for(int i=0;i<treeSourceArray.size();i++){
            JSONObject treeObject = treeSourceArray.getJSONObject(i);
            treeObject.put("children",null);
            Integer pId = treeObject.getInteger(parentId);
            if(convertTreeMap.containsKey(pId)){
                convertTreeMap.get(pId).add(treeObject);
            }else {
                List<JSONObject> list = new ArrayList<>();
                list.add(treeObject);
                convertTreeMap.put(pId,list);
                parentIds.add(pId);
            }

        }
        return tree(convertTreeMap,parentIds,id);
    }

    /**
     * 根据顶级节点数据，遍历其子节点数据
     * @param convertTreeMap
     * @param parentIds
     * @param id
     * @return
     */
    private static List<JSONObject> tree(Map<Integer,List<JSONObject>> convertTreeMap,List<Integer> parentIds,String id){
        for(Integer pId : parentIds){
           List<JSONObject> childList = convertTreeMap.get(pId);
           convert(convertTreeMap,childList,id);
        }
        List<JSONObject> resultList = new ArrayList<>();
        for(Integer key : convertTreeMap.keySet()){
            resultList.addAll(convertTreeMap.get(key));
        }
        return resultList;
    }

    /**
     * 循环处理
     * @param convertTreeMap
     * @param treeList
     * @param id
     */
    private static void convert(Map<Integer,List<JSONObject>> convertTreeMap, List<JSONObject> treeList,String id){
        if(null == treeList){
            return;
        }
        for(JSONObject treeObject : treeList){
            List<JSONObject> childList = convertTreeMap.get(treeObject.getInteger(id));
            if(null != childList){
                treeObject.put("children",childList);
                convertTreeMap.remove(treeObject.getInteger(id));
                convert(convertTreeMap,childList,id);
            }
        }
    }
}
