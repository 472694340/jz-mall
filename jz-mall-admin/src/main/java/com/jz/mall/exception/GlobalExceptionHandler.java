package com.jz.mall.exception;

import com.jz.mall.common.CommonResult;
import com.jz.mall.exception.base.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    /**
     * 如果抛出BaseException这个异常,就会进入这里
     * @param base
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public CommonResult handleBaseException(BaseException base){
        if (base.getErrorCode()!=null){
            return CommonResult.failed(base.getErrorCode());
        }
        return CommonResult.failed(base.getMessage());
    }

    /**
     * 参数绑定异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public CommonResult handValidException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.getFieldError() != null){
            message = bindingResult.getFieldError().getDefaultMessage() + bindingResult.getFieldError().getField();
        }
        return CommonResult.validateFailed(message);
    }

    /**
     * 参数错误异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handMethodArgumentException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.getFieldError() != null){
            FieldError fieldError = bindingResult.getFieldError();
            message = fieldError.getDefaultMessage() + fieldError.getField();
        }
        return CommonResult.validateFailed(message);
    }


    /**
     * 其他未自定义的异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public CommonResult handleException(Exception e){
        log.error("异常发生的原因是:"+  e.getMessage());
        return CommonResult.failed(e.getMessage());
    }




}
