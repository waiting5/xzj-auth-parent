package com.xzj.sys.entity;

/**
 * 类名
 *
 * @ClassName SysDictType
 * 简述此类作用
 * @Description 字典类型表
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/17 19:56
 * 版本
 * @Version 1.0
 **/
import com.baomidou.mybatisplus.annotation.IdType;
import com.xzj.sys.entity.base.BaseField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@ApiModel(value = "字典类型表",description = "")
@TableName("sys_dict_type")
@Data
public class SysDictType extends BaseField {

    /** 字典主键 */
    @ApiModelProperty(name = "字典主键",notes = "")
    @TableId(type = IdType.AUTO)
    private Integer dictId ;
    /** 字典名称 */
    @ApiModelProperty(name = "字典名称",notes = "")
    private String dictName ;
    /** 字典类型 */
    @ApiModelProperty(name = "字典类型",notes = "")
    private String dictType ;

    @ApiModelProperty(name = "父类ID",notes = "")
    private Integer parentId;
}
