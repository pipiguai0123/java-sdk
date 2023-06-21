package com.coinpay.sdk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    CONFIG_NOT_NULL(0, "配置不能为空"),
    PARAM_NOT_NULL(1, "参数不能为空"),
    SIGN_ERROR(2, "校验响应签名异常"),
    ;

    private final int code;

    private final String message;


}
