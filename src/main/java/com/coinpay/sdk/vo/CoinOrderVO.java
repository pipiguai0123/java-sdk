package com.coinpay.sdk.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CoinOrderVO implements Serializable {

    /**
     * 流水号
     */
    private String trade_no;

    /**
     * 订单状态：1等待支付 2支付成功 3交易失败 4交易超时
     */
    private Integer status;


    /**
     * eth收款地址
     */
    private String eth_address;

    /**
     * tron收款地址
     */
    private String tron_address;

    /**
     * 商户订单标题
     */
    private String subject;

    /**
     * 商户订单号
     */
    private String order_no;

    /**
     * 回调地址
     */
    private String notify_url;

    /**
     * 超时时间(分钟)
     */
    private Long time_out;

    /**
     * 数量
     */
    private String amount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 国际化：中文简体（zh-CN），中文繁体(zh-TW)，英文(en-US)，日文(ja-JP)
     */
    private String locale;

    /**
     * 过期时间
     */
    private Long expire_time;

    /**
     * 创建时间
     */
    private Long create_time;
}
