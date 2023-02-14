package com.xzj.sys.entity.vo;

import com.xzj.sys.entity.SysDept;
import lombok.Data;

import java.util.List;

/**
 * @Category com.xzj.sys.entity
 * @Description 【组织树实体类】
 * @Author xiaj07
 * @Since 2023/1/18 16:06
 **/
@Data
public class SysDeptVo extends SysDept {


    /**
     * 子对象信息
     */
    private List<SysDeptVo> children;

}
