package com.zyzh.controller;

import com.zyzh.service.TestService;
import com.zyzh.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

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

    /**
     * 接口缓存
     * @param id
     * @return
     */
    @Cacheable(value = "USER",key = "#id",condition = "#id.equals('1')")
    @GetMapping("/user")
    public Result user(String id) {
        logger.info("请求参数:{}", id);
        return Result.success(new Date());
    }


    @CachePut(value = "USER",key = "#id")
    @GetMapping("/putUser")
    public Result putUser(String id) {
        logger.info("请求参数:{}", id);
        return Result.success(new Date());
    }

    /**
     * 删除缓存
     * @param id
     * @return
     */
    @CacheEvict(value = "USER",key = "#id")
    @GetMapping("/delUser")
    public Result delUser(String id) {
        logger.info("请求参数:{}", id);
        return Result.success(new Date());
    }

}