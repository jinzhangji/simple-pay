package com.simple.param.wechatpay;

import com.simple.result.wechatpay.WeChatCloseOrderResult;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatPayCloseOrderParam extends WeChatAbstractSimplePayParam<WeChatCloseOrderResult> {

    private static final String URL = "https://api.mch.weixin.qq.com/pay/closeorder";

    private String out_trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    @Override
    public String requestURI() {
        return URL;
    }

    @Override
    public Class<WeChatCloseOrderResult> resClass() {
        return WeChatCloseOrderResult.class;
    }
}
