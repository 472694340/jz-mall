package com.jz.mall.exception;

import com.jz.mall.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理,
 *  做一件事之前,先理清思路,如何做,会不会出现什么问题
 *  通过@RestControllerAdvice 开启全局异常处理,针对Controller修饰的类
 *  常用的异常处理,可以直接在Enum类中定义好,直接返回
 *  非常见的异常,这里这里处理
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public CommonResult handleException(Exception e){
        log.error("异常发生的原因是:"+  e.getMessage());
        return CommonResult.failed(e.getMessage());
    }




}
