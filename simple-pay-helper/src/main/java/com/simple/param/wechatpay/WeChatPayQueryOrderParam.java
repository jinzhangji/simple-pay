package com.simple.param.wechatpay;

import com.simple.result.wechatpay.WeChatPayQueryOrderResult;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatPayQueryOrderParam extends WeChatAbstractSimplePayParam<WeChatPayQueryOrderResult> {

    private static final String URL = "https://api.mch.weixin.qq.com/pay/orderquery";

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
    public Class<WeChatPayQueryOrderResult> resClass() {
        return WeChatPayQueryOrderResult.class;
    }
}
