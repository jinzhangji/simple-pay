package com.simple.core.wechat;

import com.simple.core.SimplePayProperties;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatPayProperties extends SimplePayProperties {

    private Long id;

    private String appId;

    private String secret;
    //签名signKey
    private String signKey;
    //商户号
    private String mchid;
    //pk12证书路径
    private String pk12Path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getPk12Path() {
        return pk12Path;
    }

    public void setPk12Path(String pk12Path) {
        this.pk12Path = pk12Path;
    }


}
