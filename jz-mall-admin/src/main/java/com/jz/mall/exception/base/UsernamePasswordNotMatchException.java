package com.jz.mall.exception.base;

import com.jz.mall.utils.HttpsStatus;

public class UsernamePasswordNotMatchException extends BaseException {
    private static final long serialVersionUID = 1L;
    public UsernamePasswordNotMatchException(){
        super("用户密码不正确", HttpsStatus.HTTP_BAD_REQUEST);
    }
}
