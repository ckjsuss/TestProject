package com.zyzh.service.impl;

/**
 * TODO 类描述
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-21 16:32
 */
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zyzh.service.TestService;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {

    @Override
    @SentinelResource(value = "sayHello")
    public String sayHello(String name) {
        // 测试方法
        return "Hello, " + name;
    }
}

