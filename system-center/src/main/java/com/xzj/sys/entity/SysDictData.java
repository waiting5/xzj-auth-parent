package com.xzj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.xzj.sys.entity.base.BaseField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 类名 @ClassName SysDictData
 * 简述此类作用 @Description 字典数据表
 * 作者 @Author xiajun
 * 创建时间 @Date 2023/1/17 20:08
 * 版本 @Version 1.0
 **/
@ApiModel(value = "字典数据表",description = "")
@TableName("sys_dict_data")
@Data
public class SysDictData extends BaseField {

    /** 字典编码 */
    @ApiModelProperty(name = "字典编码",notes = "")
    @TableId(type = IdType.AUTO)
    private Integer dictCode ;
    /** 字典排序 */
    @ApiModelProperty(name = "字典排序",notes = "")
    private Integer dictSort ;
    /** 字典标签 */
    @ApiModelProperty(name = "字典标签",notes = "")
    private String dictLabel ;
    /** 字典键值 */
    @ApiModelProperty(name = "字典键值",notes = "")
    private String dictValue ;
    /** 字典类型 */
    @ApiModelProperty(name = "字典类型",notes = "")
    private String dictType ;

}
