package com.lianji.globalException;

import lombok.Data;

@Data
public class AppException extends  RuntimeException{
    private int code=500;
    private String msg="服务器异常";
    public AppException(AppExceptionCodeMsg appExceptionCodeMsg) {
        super();
        this.code=appExceptionCodeMsg.getCode();
    }



}
