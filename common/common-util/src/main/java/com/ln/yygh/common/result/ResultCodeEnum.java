package com.ln.yygh.common.result;

import lombok.Getter;

/**
 * @Description:
 * @Author: Euphratica
 * @Date: 2022/01/17
 */
@Getter // 只需要提供 getter 方法即可
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(201, "失败");

    private int code;
    private String message;

    private ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
