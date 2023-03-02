package com.jz.mall.exception.annotationException;

public class NotPermissionException extends RuntimeException {
    private static final long serialVersionUID =1L;
    public NotPermissionException(Throwable e){
        super("暂无权限");
    }
}
