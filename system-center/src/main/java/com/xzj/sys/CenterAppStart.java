package com.xzj.sys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 类名
 *
 * @ClassName CenterAppStart
 * 简述此类作用
 * @Description TODO
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/15 15:51
 * 版本
 * @Version 1.0
 **/
@SpringBootApplication
@Slf4j
public class CenterAppStart {

    public static void main(String[] args) {
        SpringApplication.run(CenterAppStart.class,args);
        log.info("(♥◠‿◠)ﾉﾞ  系统管理启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
