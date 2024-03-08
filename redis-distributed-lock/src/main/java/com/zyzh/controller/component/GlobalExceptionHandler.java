package com.zyzh.controller.component;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO 类描述
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-02-21 9:05
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, IllegalArgumentException e){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("msg",e.getMessage());
        mv.addObject("url",request.getRequestURL());
        mv.setViewName("404");
        return mv;
    }
}
