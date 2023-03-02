package com.jz.mall.common.api;


import com.jz.mall.utils.HttpsStatus;

public enum EnumResult implements ErrorCode {
    SUCCESS("操作成功", HttpsStatus.HTTP_OK),
    FAIL("操作失败", HttpsStatus.HTTP_INTERNAL_ERROR),
    UNAUTHORIZED("未验证或token已过期", HttpsStatus.HTTP_UNAUTHORIZED),
    FORBIDDEN("被禁止类型", HttpsStatus.HTTP_FORBIDDEN);
    private String message;
    private Integer code;

    EnumResult() {
    }

    EnumResult(String msg, Integer code) {
        this.message = msg;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }


    @Override
    public Integer getCode() {
        return code;
    }

}
