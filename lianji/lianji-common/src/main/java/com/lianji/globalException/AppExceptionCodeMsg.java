package com.lianji.globalException;


public enum AppExceptionCodeMsg {

    SYSTEM_ERROR(500, "系统内部错误"),
    INVALID_CODE(10000, "验证码无效"),
    INVALID_USER(10001, "用户名或密码错误"),
    ISN_NOT_EXIST(10002, "用户不存在"),
    ISN_NOT_USER(10003, "用户名密码不为空"),
    USER_DISABLED(10004,"用户被禁用");
//    INVALID_TOKEN(10004, "无效的token"),
//    INVALID_PARAM(10005, "参数无效"),
//    INVALID_FILE(10006, "文件无效"),
//    INVALID_FILE_TYPE(10007, "文件类型无效"),
//    INVALID_FILE_SIZE(10008, "文件大小无效"),
//    INVALID_FILE_FORMAT(10009, "文件格式无效");


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
