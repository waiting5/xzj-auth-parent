package com.xzj.sys.entity.base;

import lombok.Data;

import java.util.List;

/**
 * @ClassName [类名 : BaseTree ]
 * @Description [简述 : TODO]
 * @Author [作者 : xiajun]
 * @Date [创建时间 : 2023/1/18 19:18]
 * @Version [版本 : 1.0]
 **/
@Data
public class BaseTree<T> {

    /**
     * 子对象信息
     */
    private List<T> children;
}
