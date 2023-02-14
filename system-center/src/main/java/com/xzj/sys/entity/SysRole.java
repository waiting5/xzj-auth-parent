package com.xzj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xzj.sys.entity.base.BaseField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 类名
 *
 * @ClassName SysRole
 * 简述此类作用
 * @Description TODO
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/17 19:23
 * 版本
 * @Version 1.0
 **/
@Data
@ApiModel(value = "角色信息表",description = "")
@TableName("sys_role")
public class SysRole extends BaseField {

    /** 角色ID */
    @ApiModelProperty(name = "角色ID",notes = "")
    @TableId(type = IdType.AUTO)
    private Integer roleId ;
    /** 角色名称 */
    @ApiModelProperty(name = "角色名称",notes = "")
    private String roleName ;
    /** 角色别名 */
    @ApiModelProperty(name = "角色别名",notes = "")
    private String roleAlias ;
    /** 显示顺序 */
    @ApiModelProperty(name = "显示顺序",notes = "")
    private Integer roleSort ;
}
