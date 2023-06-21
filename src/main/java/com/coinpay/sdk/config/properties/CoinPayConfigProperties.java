package com.coinpay.sdk.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@ConfigurationProperties(prefix = "coin.pay")
public class CoinPayConfigProperties implements Serializable {

    /**
     * 客户端身份标识
     */
    private String appId;

    /**
     * 私钥加密(默认方式)
     */
    private String privateKey;

    /**
     * 私钥文件地址
     */
    private String privateKeyFile;

    /**
     * 公钥验签(默认方式)
     */
    private String publicKey;

    /**
     * 公钥文件地址
     */
    private String publicKeyFile;

    /**
     * 请求地址前缀
     */
    private String url;
}
