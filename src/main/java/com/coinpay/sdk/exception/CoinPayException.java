package com.coinpay.sdk.exception;

import cn.hutool.core.util.StrUtil;
import com.coinpay.sdk.enums.ExceptionEnum;
import lombok.Data;

@Data
public class CoinPayException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;


    public CoinPayException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CoinPayException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMessage();
    }

    public CoinPayException(ExceptionEnum exceptionEnum, String msg) {
        super(StrUtil.format(exceptionEnum.getMessage(),msg));
        this.code = exceptionEnum.getCode();
        this.msg = StrUtil.format(exceptionEnum.getMessage(),msg);
    }
}
