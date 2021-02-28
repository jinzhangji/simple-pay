package com.simple.param.wechatpay;

import com.simple.result.wechatpay.WeChatAuthSnsUserInfoResult;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public class WeChatAuthSnsUserInfoParam extends WeChatAbstractSimplePayParam<WeChatAuthSnsUserInfoResult> {

    private static final String URL = "https://api.weixin.qq.com/sns/userinfo";

    private String access_token;
    private String openid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String requestURI() {
        return URL;
    }

    @Override
    public Class<WeChatAuthSnsUserInfoResult> resClass() {
        return WeChatAuthSnsUserInfoResult.class;
    }
}
