package com.simple.core;

/**
 * Created by Jin.Z.J  2021/2/10
 */
public class SimplePayProperties {

    //统一支付回调通知地址
    private String notifyUrl;
    //统一退款回调地址
    private String refundNotifyUrl;
    //重定向个地址
    private String redirectUrl;
    //应用类型
    private String appType;

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

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
