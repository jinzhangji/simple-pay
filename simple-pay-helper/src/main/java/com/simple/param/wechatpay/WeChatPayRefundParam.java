package com.simple.param.wechatpay;

import com.simple.result.wechatpay.WeChatPayRefundResult;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatPayRefundParam extends WeChatAbstractSimplePayParam<WeChatPayRefundResult> {

    private String transaction_id;
    private String out_trade_no;
    private String out_refund_no;
    private Integer total_fee;
    private Integer refund_fee;
    private String refund_fee_type;
    private String refund_desc;
    private String refund_account;
    private String notify_url;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    @Override
    public String requestURI() {
        return "https://api.mch.weixin.qq.com/secapi/pay/refund";
    }

    @Override
    public Class<WeChatPayRefundResult> resClass() {
        return WeChatPayRefundResult.class;
    }
}
