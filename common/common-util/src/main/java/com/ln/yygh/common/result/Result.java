package com.ln.yygh.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

/**
 * @Description:
 * @Author: Euphratica
 * @Date: 2022/01/17
 */
@Data
@ApiModel("全局返回统一结果")
public class Result<T> {

    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 创建一个带有状态码、消息和数据的结果对象
     */
    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        return new Result<T>(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data);
    }

    /**
     * 创建一个带有状态码、消息，数据为 null 的结果对象
     */
    public static <T> Result<T> build(ResultCodeEnum resultCodeEnum) {
        return new Result<T>(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }

    /**
     * 创建一个带有状态码、消息，数据为 null 的结果对象
     */
    public static <T> Result<T> build(int code, String message) {
        return new Result<T>(code, message, null);
    }

    /**
     * 操作成功
     */
    public static <T> Result<T> ok() {
        return build(ResultCodeEnum.SUCCESS);
    }

    /**
     * 操作成功
     */
    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * 操作失败
     */
    public static <T> Result<T> fail() {
        return build(ResultCodeEnum.FAIL);
    }

    /**
     * 操作失败
     */
    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }
}
