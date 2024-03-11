package com.zyzh.config;

/**
 * 拦截配置
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-11 9:36
 */

import com.zyzh.intercept.TestIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private TestIntercept testIntercept;

    /**
     * 注册拦截器，指定拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testIntercept).addPathPatterns("/**/api/**");
    }
}

