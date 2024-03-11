package com.zyzh.intercept;

/**
 * 测试拦截器
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-11 9:34
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TestIntercept implements HandlerInterceptor {

    /**
     * 在请求处理之前进行操作
     * @param request
     * @param response
     * @param handler
     * @return  返回 true 表示继续执行后续操作，返回 false 则表示拦截请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("Interceptor: preHandle method is called");
        return true;
    }

    /**
     *  在请求处理之后进行操作
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor: postHandle method is called");
    }

    /**
     * 在请求完成之后进行操作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        System.out.println("Interceptor: afterCompletion method is called");
    }
}

