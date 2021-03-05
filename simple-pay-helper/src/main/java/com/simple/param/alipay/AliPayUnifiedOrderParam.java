package com.simple.param.alipay;

import com.simple.result.alipay.AliPayUnifiedOrderResult;

/**
 * 支付宝下单参数[基础参数]
 * Created by Jin.Z.J  2020/11/25
 */
public class AliPayUnifiedOrderParam extends AliAbstractSimplePayParam<AliPayUnifiedOrderResult> {


    private Long orderId;
    private Double total_amount;
    private String subject;
    private String out_trade_no;
    private String product_code;
    private String notify_url;
    private String return_url;
    private String timeout_express;
    private String time_expire;//绝对超时时间
    private String body;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }


    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public String requestURI() {
        return null;
    }

    @Override
    public Class<AliPayUnifiedOrderResult> resClass() {
        return AliPayUnifiedOrderResult.class;
    }



}
