package com.jz.mall.common;
import com.jz.mall.common.api.*;
import lombok.Data;

/**
 *  封装返回信息工具类
 * @param <T>
 */
@Data

public class CommonResult<T> {
    private String message;
    private Integer code;
    private T data;//封装数据

    public CommonResult(String message) {
        this.message = message;
    }

    /**
     *  成功返回数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(EnumResult.SUCCESS.getMessage(),EnumResult.SUCCESS.getCode(),data);
    }

    /**
     * 成功返回预定信息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(){
        return new CommonResult<T>(EnumResult.SUCCESS.getMessage(),EnumResult.SUCCESS.getCode());
    }


    /**
     * 失败返回自定义信息
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(ErrorCode errorCode){
        return new CommonResult<T>(errorCode.getMessage(),errorCode.getCode(),null);
    }

    /**
     * 失败返回message
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(message,EnumResult.FAIL.getCode());
    }

    public static <T> CommonResult<T> failed() {
        return new CommonResult<T>(EnumResult.FAIL.getMessage(),EnumResult.FAIL.getCode(),null);
    }

    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<>(message,EnumResult.VAILDATE_FAILED.getCode());
    }


    public CommonResult() {
    }

    public CommonResult(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CommonResult(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }


    /**
     * 未验证
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<>(EnumResult.UNAUTHORIZED.getMessage(),EnumResult.UNAUTHORIZED.getCode(),data);
    }
    public static <T> CommonResult<T> unauthorized() {
        return new CommonResult<>(EnumResult.UNAUTHORIZED.getMessage(),EnumResult.UNAUTHORIZED.getCode(),null);
    }

    /**
     * 禁止
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<>(EnumResult.FORBIDDEN.getMessage(),EnumResult.FORBIDDEN.getCode(),data);
    }
}
