package com.simple.param.alipay;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;

/**
 * Created by Jin.Z.J  2021/2/8
 */
public class AliSystemOauthTokenParam extends AliAbstractSimplePayParam<AlipaySystemOauthTokenResponse> {

    private String grantType = "authorization_code"; //授权方式。支持：authorization_code：使用应用授权码换取应用授权令牌app_auth_token。    refresh_token：使用app_refresh_token刷新获取新授权令牌。
    private String code;//授权码，应用授权后得到。本参数在 grant_type 为 authorization_code 时必填；为 refresh_token 时不填。
    private String refreshToken;//刷新令牌，上次换取访问令牌时得到。本参数在 grant_type 为 authorization_code 时不填；为 refresh_token 时必填，且该值来源于此接口的返回值 app_refresh_token（即至少需要通过 grant_type=authorization_code 调用此接口一次才能获取）

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String requestURI() {
        return null;
    }


    @Override
    public Class<AlipaySystemOauthTokenResponse> resClass() {
        return AlipaySystemOauthTokenResponse.class;
    }
}
