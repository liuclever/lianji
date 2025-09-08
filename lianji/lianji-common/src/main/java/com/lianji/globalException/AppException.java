package com.lianji.globalException;

import lombok.Data;

@Data
public class AppException extends  RuntimeException{
    private int code=500;
    private String msg="服务器异常";

    public AppException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public AppException(AppExceptionCodeMsg appExceptionCodeMsg) {
        super(appExceptionCodeMsg.getMsg()); // 设置父类的message
        this.code = appExceptionCodeMsg.getCode();
        this.msg = appExceptionCodeMsg.getMsg();
    }



}
