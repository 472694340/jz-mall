package com.jz.mall.exception.base;



/**
 *  采用另外一种思想,直接在代码里
 */
public class BaseException extends RuntimeException{

    private String message;//错误信息

    private Integer code;//错误代码

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BaseException() {
    }

    public BaseException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}

