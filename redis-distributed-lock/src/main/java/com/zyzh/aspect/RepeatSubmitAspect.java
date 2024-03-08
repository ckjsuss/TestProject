package com.zyzh.aspect;

/**
 * 切面
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-08 8:58
 */

import com.alibaba.fastjson.JSON;
import com.zyzh.annotation.RepeatSubmit;
import com.zyzh.utils.HttpCode;
import com.zyzh.utils.IpUtil;
import com.zyzh.utils.RequestContextUtil;
import com.zyzh.utils.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @Description 使用aop技术防止重复请求操作, 通过 ip+url+token+参数 拼接成key
 */
@Aspect
@Component
public class RepeatSubmitAspect {
    @Resource
    private RedisTemplate redisTemplate;

    private static Logger logger = LoggerFactory.getLogger(RepeatSubmitAspect.class);

    /**
     * 切点,通过注解来做切入点
     */
    @Pointcut("@annotation(com.zyzh.annotation.RepeatSubmit)")
    public void pt() {
    }

    @Around("pt()") // ProceedingJoinPoint是继承JoinPoint,只能用于环绕通知.
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 生成key
        // RequestContextUtil是我自己定义的工具类,底层是通过RequestContextHolder获得当前请求的request对象
        HttpServletRequest request = RequestContextUtil.getRequest();
        // IPUtil也是我自定义的工具类,专门用于获取请求的ip
        String ip = IpUtil.getIpAddress(request);
        String uri = request.getRequestURI();
        // 得到参数
        Object[] args = joinPoint.getArgs();
        // 因为我的项目全是json格式传输,所以只要得到数组的第一个就行
        String params = JSON.toJSONString(args[0]);
        // 拼接key
        StringBuffer key = new StringBuffer();
        key.append(ip);
        key.append(uri);
        key.append(params);
        // 判断redis中是否已经存在此请求的key
        if (redisTemplate.hasKey(key.toString())) {
            // 返回错误信息
            return Result.fail(HttpCode.REPEAT_ERROR);
        }
        // 得到注解上设置的超时时间
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RepeatSubmit repeatSubmit = method.getAnnotation(RepeatSubmit.class);
        long time = repeatSubmit.time();
        // 存入redis中
        try {
            logger.info("key:{},value:{},timeout:{}", key, uri + params, time);
            redisTemplate.opsForValue().set(key.toString(), uri + params, time, TimeUnit.DAYS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 正常执行
        Object result = joinPoint.proceed();
        return result;
    }
}


