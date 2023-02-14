package com.xzj.sys.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Category com.xzj.mysql.plus.common.domain
 * @Description 【功能描述】
 * @Author xiaj07
 * @Since 2023/1/12 11:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBaseFiled {

    /**
     * 条数
     */
    private int pageSize = 10;

    /**
     * 页码
     */
    private int page = 1;

}
