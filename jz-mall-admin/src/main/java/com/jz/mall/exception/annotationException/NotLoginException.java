package com.jz.mall.exception.annotationException;

public class NotLoginException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NotLoginException(){
        super("该用户未登录!");
    }
}
