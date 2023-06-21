package com.coinpay.sdk.service;

import com.coinpay.sdk.dto.OrderCreateDTO;
import com.coinpay.sdk.dto.OrderQueryDTO;
import com.coinpay.sdk.result.CoinPayResult;
import com.coinpay.sdk.vo.AccountBalanceVO;
import com.coinpay.sdk.vo.CoinOrderVO;

public interface CoinOrderService {


    /**
     * 创建订单
     */
    CoinPayResult<CoinOrderVO> orderCreate(OrderCreateDTO dto);

    /**
     * 查询订单
     */
    CoinPayResult<CoinOrderVO> orderQuery(OrderQueryDTO dto);


    /**
     * 关闭订单
     */
    CoinPayResult<Void> closeOrder(OrderQueryDTO dto);


    /**
     * 获取商户资产
     */
    CoinPayResult<AccountBalanceVO> getBalance();

}
