package com.simple.properties;

import com.simple.core.alipay.AliPayProperties;
import com.simple.core.wechat.WeChatPayProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付相关配置类
 * Created by Jin.Z.J  2020/11/26
 */
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "simple-pay")
public class StatSimplePayProperties {

    //统一支付回调通知地址
    private String notifyUrl;
    //统一退款回调地址
    private String refundNotifyUrl;
    //h5重定向跳转地址
    private String redirectUrl;

    private boolean wechatDb = false;

    private boolean aliDb = false;

    private Map<String,List<StatWeChatPayProperties>> wechat = new HashMap<>();

    private Map<String,List<StatAliProperties>> ali = new HashMap<>();


    public static final class StatWeChatPayProperties extends WeChatPayProperties {

    }

    public static final class StatAliProperties extends AliPayProperties {

    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRefundNotifyUrl() {
        return refundNotifyUrl;
    }

    public void setRefundNotifyUrl(String refundNotifyUrl) {
        this.refundNotifyUrl = refundNotifyUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public boolean isWechatDb() {
        return wechatDb;
    }

    public void setWechatDb(boolean wechatDb) {
        this.wechatDb = wechatDb;
    }

    public boolean isAliDb() {
        return aliDb;
    }

    public void setAliDb(boolean aliDb) {
        this.aliDb = aliDb;
    }

    public Map<String, List<StatWeChatPayProperties>> getWechat() {
        return wechat;
    }

    public void setWechat(Map<String, List<StatWeChatPayProperties>> wechat) {
        this.wechat = wechat;
    }

    public Map<String, List<StatAliProperties>> getAli() {
        return ali;
    }

    public void setAli(Map<String, List<StatAliProperties>> ali) {
        this.ali = ali;
    }
}
