package com.coinpay.sdk.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoinPayResult<T> implements Serializable {

    private int code;

    private String message;

    private T data;


}
