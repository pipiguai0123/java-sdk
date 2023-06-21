package com.coinpay.sdk.service.impl;

import cn.hutool.core.util.StrUtil;
import com.coinpay.sdk.config.properties.CoinPayConfigProperties;
import com.coinpay.sdk.constants.CoinPayUrlConstant;
import com.coinpay.sdk.dto.OrderCreateDTO;
import com.coinpay.sdk.dto.OrderQueryDTO;
import com.coinpay.sdk.enums.ExceptionEnum;
import com.coinpay.sdk.exception.CoinPayException;
import com.coinpay.sdk.result.CoinPayResult;
import com.coinpay.sdk.service.CoinOrderService;
import com.coinpay.sdk.util.CoinPayRequestUtil;
import com.coinpay.sdk.vo.AccountBalanceVO;
import com.coinpay.sdk.vo.CoinOrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoinOrderServiceImpl implements CoinOrderService {

    @Autowired
    private CoinPayConfigProperties coinPayConfigProperties;

    /**
     * 创建订单
     */
    @Override
    public CoinPayResult<CoinOrderVO> orderCreate(OrderCreateDTO dto) {
        if (dto == null) {
            throw new CoinPayException(ExceptionEnum.PARAM_NOT_NULL);
        }
        return CoinPayRequestUtil.orderRequest(CoinPayUrlConstant.ORDER_CREATE, dto, coinPayConfigProperties);
    }

    /**
     * 查询订单
     */
    @Override
    public CoinPayResult<CoinOrderVO> orderQuery(OrderQueryDTO dto) {
        if (dto == null) {
            throw new CoinPayException(ExceptionEnum.PARAM_NOT_NULL);
        }
        if(StrUtil.isBlank(dto.getOrder_no()) && StrUtil.isBlank(dto.getTrade_no())){
            throw new CoinPayException(ExceptionEnum.PARAM_NOT_NULL);
        }
        return CoinPayRequestUtil.orderRequest(CoinPayUrlConstant.ORDER_QUERY, dto, coinPayConfigProperties);
    }


    @Override
    public CoinPayResult<Void> closeOrder(OrderQueryDTO dto) {
        if (dto == null) {
            throw new CoinPayException(ExceptionEnum.PARAM_NOT_NULL);
        }
        if(StrUtil.isBlank(dto.getOrder_no()) && StrUtil.isBlank(dto.getTrade_no())){
            throw new CoinPayException(ExceptionEnum.PARAM_NOT_NULL);
        }
        return CoinPayRequestUtil.orderRequest(CoinPayUrlConstant.CLOSE_ORDER, dto, coinPayConfigProperties);
    }


    @Override
    public CoinPayResult<AccountBalanceVO> getBalance() {
        return CoinPayRequestUtil.orderRequest(CoinPayUrlConstant.GET_BALANCE, null, coinPayConfigProperties);
    }
}
