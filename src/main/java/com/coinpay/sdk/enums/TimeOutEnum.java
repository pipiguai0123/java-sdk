package com.coinpay.sdk.enums;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举
 */
@Getter
@AllArgsConstructor
public enum TimeOutEnum {
    level5(5, "5分钟"),
    level10(10, "10分钟"),
    level15(15, "15分钟"),
    level20(20, "20分钟"),
    level25(25, "25分钟"),
    level30(30, "30分钟");


    private final long level;

    private final String message;


    @JSONField
    public long getLevel() {
        return level;
    }
}
