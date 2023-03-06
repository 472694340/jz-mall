package com.jz.mall.exception.base;
import com.jz.mall.common.api.*;

/**
 * 继承父类的方法,通过这个类,自定义抛出去的信息
 *  还可以继承这个BaseException ,抛出其他自定义异常
 */
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;



    public ErrorCode getErrorCode() {
        return errorCode;
    }


    /**
     * 将ErrorCode 里的属性注入到父类中
     * @param errorCode
     */
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(Throwable cause){
        super(cause);
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(String message,Throwable cause){
        super(message,cause);
    }



}
