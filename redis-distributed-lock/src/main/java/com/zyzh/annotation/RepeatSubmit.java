package com.zyzh.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-08 8:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
    String value() default "";
    long time() default 1; // 单位:s

}

