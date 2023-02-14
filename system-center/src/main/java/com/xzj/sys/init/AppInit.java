package com.xzj.sys.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * 类名
 *
 * @ClassName AppInit
 * 简述此类作用
 * @Description TODO
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/15 16:12
 * 版本
 * @Version 1.0
 **/
@Component
@Slf4j
public class AppInit implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //log.info("{}",sysDeptMapper.selectList(null));
    }
}
