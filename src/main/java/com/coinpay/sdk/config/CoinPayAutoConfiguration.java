package com.coinpay.sdk.config;


import com.coinpay.sdk.config.properties.CoinPayConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.coinpay.sdk")
@EnableConfigurationProperties(value = {CoinPayConfigProperties.class})
public class CoinPayAutoConfiguration {
}
