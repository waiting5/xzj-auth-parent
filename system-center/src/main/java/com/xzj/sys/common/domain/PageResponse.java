package com.xzj.sys.common.domain;

import lombok.Data;

import java.util.List;

/**
 * @Category com.xzj.mysql.plus.common.domain
 * @Description 【功能描述】
 * @Author xiaj07
 * @Since 2023/1/12 14:23
 **/
@Data
public class PageResponse<T> extends PageBaseFiled {

    /**
     * 总条数
     */
    private long total;

    /**
     * 查询结果
     */
    private List<T> rows;

    private PageResponse(int pageSize,int pageNum){
        super(pageSize,pageNum);
    }

    public static PageResponse init(int pageSize,int pageNum){
        return new PageResponse(pageSize,pageNum);
    }

    public PageResponse putData(List<T> data){
        this.rows = data;
        return this;
    }

}
