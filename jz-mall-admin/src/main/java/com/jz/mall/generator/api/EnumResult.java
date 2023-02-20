package com.jz.mall.generator.api;



public enum EnumResult implements ErrorCode {
    SUCCESS("操作成功",200),FAIL("操作失败",500);
    private String message;
    private Integer code;





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
