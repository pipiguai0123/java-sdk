package com.coinpay.sdk.util;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.coinpay.sdk.config.properties.CoinPayConfigProperties;
import com.coinpay.sdk.constants.CoinPayHeaderConstant;
import com.coinpay.sdk.enums.ExceptionEnum;
import com.coinpay.sdk.exception.CoinPayException;
import com.coinpay.sdk.result.CoinPayResult;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CoinPayRequestUtil<T> {


    /**
     * 请求封装
     *
     * @param url   请求地址
     * @param param JSON请求参数
     */
    public static <T> CoinPayResult<T> orderRequest(String url, Object param, CoinPayConfigProperties config) {
        if (StrUtil.isBlank(config.getUrl()) || StrUtil.isBlank(config.getAppId())) {
            throw new CoinPayException(ExceptionEnum.CONFIG_NOT_NULL);
        }

        //组装url
        url = config.getUrl() + url;
        url = URLUtil.normalize(url); //格式化url


        String privateKey = null;
        String publicKey = null;
        if (StrUtil.isNotBlank(config.getPublicKeyFile()) && StrUtil.isNotBlank(config.getPrivateKeyFile())) {
            FileReader publicFile = new FileReader(config.getPublicKeyFile());
            publicKey = publicFile.readString();

            FileReader privateFile = new FileReader(config.getPrivateKeyFile());
            privateKey = privateFile.readString();
        } else {
            if (StrUtil.isBlank(config.getPrivateKey()) || StrUtil.isBlank(config.getPublicKey())) {
                throw new CoinPayException(ExceptionEnum.CONFIG_NOT_NULL);
            }
            privateKey = config.getPrivateKey();
            publicKey = config.getPublicKey();
        }

        //请求头参数
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put(CoinPayHeaderConstant.appId, config.getAppId());

        if (param != null) {
            //加密请求参数
            String requestParam = SecretSignUtil.sortParam(param);
            String sign = SecretSignUtil.signPriKey(requestParam, privateKey);

            System.out.println("请求签名：" + sign);
            headerMap.put(CoinPayHeaderConstant.sign, sign);
        }

        //构建请求参数
        HttpResponse result = HttpRequest.post(url).headerMap(headerMap, true).body(JSON.toJSONString(param))//body内容
                .timeout(20000)//超时(毫秒)
                .execute();

        String body = result.body();
        System.out.println("响应数据：" + body);

        CoinPayResult<T> coinPayResult = JSON.parseObject(body, CoinPayResult.class);
        System.out.println("请求返回参数：" + coinPayResult);
        if (coinPayResult.getCode() == 10000) { //操作成功
            if (ObjectUtil.isNotEmpty(coinPayResult.getData())) {
                String responseSig = result.header(CoinPayHeaderConstant.sign); //响应签名
                System.out.println("响应签名：" + responseSig);
                //校验响应签名
                boolean is = SecretSignUtil.checkPubKey(SecretSignUtil.sortParam(coinPayResult.getData()), responseSig, publicKey);
                System.out.println("校验响应签名：" + is);
                if (!is) {
                    throw new CoinPayException(ExceptionEnum.SIGN_ERROR);
                }
            }
        }
        return coinPayResult;
    }
}
