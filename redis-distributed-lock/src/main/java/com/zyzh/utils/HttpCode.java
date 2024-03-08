package com.zyzh.utils;

/**
 * 返回值
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-08 9:13
 */
public enum HttpCode {
    success(200,"success"),
    fail(501,"fail"),
    PARAM_ERROR(502, "参数错误"),
    REPEAT_ERROR(520,"请勿重复提交" ),;

    Integer code;
    String msg;

    HttpCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
