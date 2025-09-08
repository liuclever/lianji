package com.lianji.result;


import com.lianji.globalException.AppExceptionCodeMsg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Result<T> {
    private int code=200;
    private String msg=null;
    private Object data=null;

    public Result(int code, String msg, Object data) {
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

    public static <T> Result success(int code,String msg, T data) {
        Result resp = new Result<T>(code, msg, data);
        return resp;
    }


    public static <T> Result success(T data) {
        Result resp = new Result<T>(200, "success", data);
        return resp;
    }






}
