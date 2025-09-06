package com.lianji.globalException;


import lombok.Data;


public enum AppExceptionCodeMsg {

    INVALID_CODE(10000, "验证码无效"),
    INVALID_USER(10001, "用户名或密码错误");

    private int code;
    private String msg;

    AppExceptionCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
