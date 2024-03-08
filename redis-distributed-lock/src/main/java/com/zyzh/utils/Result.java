package com.zyzh.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO 类描述
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-08 9:05
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static Result success(){
        Result result = new Result();
        result.setCode(HttpCode.success.getCode());
        result.setMsg(HttpCode.success.getMsg());
        result.setData(null);
        return result;
    }

    public static<T> Result success(T data){
        Result result = new Result();
        result.setCode(HttpCode.success.getCode());
        result.setMsg(HttpCode.success.getMsg());
        result.setData(data);
        return result;
    }

    public static Result fail(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static Result fail(HttpCode httpCode){
        return fail(httpCode.getCode(),httpCode.getMsg());
    }

}

