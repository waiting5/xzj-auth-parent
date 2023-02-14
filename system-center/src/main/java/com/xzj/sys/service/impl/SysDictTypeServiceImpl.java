package com.xzj.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzj.sys.entity.SysDictType;
import com.xzj.sys.mapper.SysDictTypeMapper;
import com.xzj.sys.service.SysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 类名
 *
 * @ClassName SysDeptServiceImpl
 * 简述此类作用
 * @Description TODO
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/15 16:17
 * 版本
 * @Version 1.0
 **/
@Slf4j
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType>
        implements SysDictTypeService {
}
