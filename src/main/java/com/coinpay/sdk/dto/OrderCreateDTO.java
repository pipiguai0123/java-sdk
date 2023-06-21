package com.coinpay.sdk.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderCreateDTO implements Serializable {

    /**
     * 商户订单标题
     */
    private String subject;

    /**
     * 商户订单号
     */
    private String order_no;

    /**
     * 数量
     */
    private String amount;

    /**
     * 回调地址
     */
    private String notify_url;

    /**
     * 超时时间(分钟)TimeOutEnum
     */
    private Long time_out;

    /**
     * LocaleEnum
     * 国际化：中文简体（zh-CN），中文繁体(zh-TW)，英文(en-US)，日文(ja-JP)
     */
    private String locale;

    /**
     * 备注
     */
    private String remark;

}
