package com.zyzh.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-08 9:03
 */
public class RequestContextUtil {

    /**
     * 得到当前请求的request对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) attributes;
        return requestAttributes.getRequest();
    }
}

