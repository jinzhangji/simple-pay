package com.simple.param.wechatpay;

import com.simple.result.wechatpay.WeChatAuthGlobalTokenResult;

/**
 * Created by Jin.Z.J  2021/2/25
 */
public class WeChatAuthGlobalTokenParam extends WeChatAbstractSimplePayParam<WeChatAuthGlobalTokenResult>{

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/token";

    private String grant_type = "client_credential";

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    @Override
    public String requestURI() {
        return URL;
    }

    @Override
    public Class resClass() {
        return WeChatAuthGlobalTokenResult.class;
    }
}
