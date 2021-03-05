package com.simple.param.alipay;

import com.alipay.api.response.AlipayTradeRefundResponse;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class AliPayRefundParam extends AliAbstractSimplePayParam<AlipayTradeRefundResponse> {


    private String out_trade_no;//	支付时传入的商户订单号，与trade_no必填一个
    private String trade_no;//	支付时返回的支付宝交易号，与out_trade_no必填一个
    private String out_request_no;//	本次退款请求流水号，部分退款时必传
    private Double refund_amount;//	本次退款金额/元

    private String notifyUrl;


    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public Double getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(Double refund_amount) {
        this.refund_amount = refund_amount;
    }

    @Override
    public String requestURI() {
        return null;
    }

    @Override
    public Class<AlipayTradeRefundResponse> resClass() {
        return null;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
