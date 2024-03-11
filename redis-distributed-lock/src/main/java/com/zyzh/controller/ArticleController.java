package com.zyzh.controller;

import com.alibaba.fastjson.JSON;
import com.zyzh.annotation.RepeatSubmit;
import com.zyzh.aspect.RepeatSubmitAspect;
import com.zyzh.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO 类描述
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-08 14:04
 */
@RestController
@RequestMapping("/v1/api/article")
public class ArticleController {

    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Resource
    private RedisTemplate redisTemplate;
    /**
     * 模糊查找文章
     */
    @PostMapping("/fuzzy")
    @RepeatSubmit(time = 1)
    public Result fuzzy(@RequestBody TestReq req){
        logger.info(JSON.toJSONString(req));
        return Result.success();
    }
}