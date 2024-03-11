package com.zyzh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Title: TestController
 * @Description:
 * @author: Leo wey
 * @date: 2023/2/28 10:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private RedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/api/deduct",method = RequestMethod.GET)
    public String deductStock() {
        int stock = Integer.parseInt(String.valueOf(stringRedisTemplate.opsForValue().get("stock")));
        if(stock > 0) {
            int realStock = stock - 1;
            stringRedisTemplate.opsForValue().set("stock",realStock + "");
            logger.info("扣减成功，剩余库存：" + realStock);
        }else {
            logger.error("扣减失败，库存不足");
        }
        return "end";
    }

    @RequestMapping(value = "/testError",method = RequestMethod.GET)
    public String testError(int age){
        if (age<=0) {
            throw new IllegalArgumentException();
        }
        return "";
    }
}