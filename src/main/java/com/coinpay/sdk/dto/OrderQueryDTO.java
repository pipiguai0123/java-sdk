package com.coinpay.sdk.dto;

import lombok.Data;

@Data
public class OrderQueryDTO {

    /**
     * 流水号
     */
    private String trade_no;

    /**
     * 商户订单号
     */
    private String order_no;

}
