package com.ln.yygh.common.exception;

import com.ln.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: Euphratica
 * @Date: 2022/01/22
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理，统一返回 Result.fail()
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception ex) {
        ex.printStackTrace();
        return Result.fail();
    }

    /**
     * 自定义全局异常处理，返回内容为异常信息
     */
    @ExceptionHandler(HospitalException.class)
    @ResponseBody
    public Result error(HospitalException ex) {
        return Result.fail(ex.getMessage());
    }
}
