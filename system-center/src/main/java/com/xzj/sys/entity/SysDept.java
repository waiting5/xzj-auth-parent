package com.xzj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xzj.sys.entity.base.BaseField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门表;
 * @author : http://www.chiner.pro
 * @date : 2023-1-15
 */
@ApiModel(value = "部门表",description = "")
@Data
public class SysDept extends BaseField {
    /** 部门id */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "部门id",notes = "")
    private Integer deptId ;
    /** 父部门id */
    @ApiModelProperty(name = "父部门id",notes = "")
    private Integer parentId ;
    /** 部门名称 */
    @ApiModelProperty(name = "部门名称",notes = "")
    private String deptName ;
    /** 显示顺序 */
    @ApiModelProperty(name = "显示顺序",notes = "")
    private Integer orderNum = 1;
    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty(name = "删除标志（0代表存在 2代表删除）",notes = "")
    private Integer delFlag = 0;

}
