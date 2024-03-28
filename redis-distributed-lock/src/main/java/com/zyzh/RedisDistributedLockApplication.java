package com.zyzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * redis 分布式锁实现
 */
@SpringBootApplication
@EnableCaching
public class RedisDistributedLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDistributedLockApplication.class, args);
    }

}
