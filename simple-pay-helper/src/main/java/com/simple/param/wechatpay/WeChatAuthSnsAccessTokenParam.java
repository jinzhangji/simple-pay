package com.simple.param.wechatpay;

import com.simple.result.wechatpay.WeChatAuthSnsAccessTokenResult;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public class WeChatAuthSnsAccessTokenParam extends WeChatAbstractSimplePayParam<WeChatAuthSnsAccessTokenResult> {

    private static final String URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    private String code;

    private String grant_type = "authorization_code";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
    public Class<WeChatAuthSnsAccessTokenResult> resClass() {
        return WeChatAuthSnsAccessTokenResult.class;
    }

}
