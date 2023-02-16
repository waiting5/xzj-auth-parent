package com.xzj.file.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName [类名 : FilePathRequest ]
 * @Description [简述 : TODO]
 * @Author [作者 : xiajun]
 * @Date [创建时间 : 2023/2/16 22:39]
 * @Version [版本 : 1.0]
 **/
@Data
public class FilePathRequest {
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 过期时间
     */
    private int expireTime;

}
