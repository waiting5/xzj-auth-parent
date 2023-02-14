package com.xzj.sys.entity.vo;

import com.xzj.sys.entity.SysDictType;
import lombok.Data;

import java.util.List;

/**
 * @Category com.xzj.sys.entity.vo
 * @Description 【功能描述】
 * @Author xiaj07
 * @Since 2023/1/28 8:38
 **/
@Data
public class SysDictTypeVo extends SysDictType {

    private List<SysDictTypeVo> child;

}
