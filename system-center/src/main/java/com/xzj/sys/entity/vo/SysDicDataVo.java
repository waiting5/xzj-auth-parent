package com.xzj.sys.entity.vo;

import com.xzj.sys.common.domain.PageBaseFiled;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Category com.xzj.sys.entity.vo
 * @Description 【功能描述】
 * @Author xiaj07
 * @Since 2023/2/14 10:04
 **/
@Data
public class SysDicDataVo extends PageBaseFiled {
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
