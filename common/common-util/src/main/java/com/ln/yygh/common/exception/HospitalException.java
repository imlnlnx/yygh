package com.ln.yygh.common.exception;

import com.ln.yygh.common.result.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: Euphratica
 * @Date: 2022/01/22
 */
@Data
@Api("自定义全局异常类")
public class HospitalException extends RuntimeException{

    @ApiModelProperty("异常状态码")
    private int code;

    /**
     * 通过状态码和错误消息创建异常对象
     */
    public HospitalException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     * 根据枚举信息创建异常
     */
    public HospitalException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
