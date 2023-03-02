package com.jz.mall.exception.annotationException;

public class UsernamePasswordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UsernamePasswordNotFoundException(){
        super("用户账号或密码错误");
    }
}
