package com.lianji.globalException;

import com.lianji.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class globalExceptionHandler{
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> Result exceptionHandler(Exception e){
        //判断是否为自定义异常
        if(e instanceof AppException) {
            AppException appException = (AppException) e;
            return Result.error(appException.getCode(),appException.getMessage());
        }
        return Result.error(500,"未知错误");
    }




}
