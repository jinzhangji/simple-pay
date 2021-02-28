package com.simple.param.wechatpay;

import com.simple.result.wechatpay.WeChatPayQueryRefundResult;

/**
 * Created by Jin.Z.J  2021/2/7
 */
public class WeChatPayQueryRefundParam extends WeChatAbstractSimplePayParam<WeChatPayQueryRefundResult> {

    private static final String URL = "https://api.mch.weixin.qq.com/pay/refundquery";

    private String     sign_type;//签名类型    sign_type   否   String(32)  HMAC-SHA256 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
    private String    transaction_id;//微信订单号   transaction_id  四选一 String(32)  1217752501201407033233368018    微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
    private String    out_trade_no;//商户订单号   out_trade_no    String(32)  1217752501201407033233368018    商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String   out_refund_no;//商户退款单号  out_refund_no   String(64)  1217752501201407033233368018    商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
    private String   refund_id;//微信退款单号  refund_id   String(32)  1217752501201407033233368018 微信生成的退款单号，在申请退款接口有返回
    private Integer  offset;//偏移量 offset  否   Int 15 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录


    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

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

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String requestURI() {
        return URL;
    }

    @Override
    public Class<WeChatPayQueryRefundResult> resClass() {
        return WeChatPayQueryRefundResult.class;
    }
}
