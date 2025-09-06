package com.lianji.result;


import com.lianji.globalException.AppExceptionCodeMsg;
import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private Object data;

    public Result(int code, String msg, Object Data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result error (AppExceptionCodeMsg appExceptionCodeMsg) {
        Result resp = new Result<T>(appExceptionCodeMsg.getCode(), appExceptionCodeMsg.getMsg(), null);
        return resp;
    }

    public static <T> Result error(int code, String msg) {
        Result resp = new Result<T>(code, msg, null);
        return resp;
    }







}
