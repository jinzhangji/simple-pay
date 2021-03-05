package com.simple.param.wechatpay;

import com.simple.result.wechatpay.WeChatAuthTicketResult;

/**
 * Created by Jin.Z.J  2021/2/25
 */
public class WeChatAuthTicketParam extends WeChatAbstractSimplePayParam<WeChatAuthTicketResult>{

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    private String access_token;
    private String type = "jsapi";

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String requestURI() {
        return URL;
    }

    @Override
    public Class<WeChatAuthTicketResult> resClass() {
        return WeChatAuthTicketResult.class;
    }
}
