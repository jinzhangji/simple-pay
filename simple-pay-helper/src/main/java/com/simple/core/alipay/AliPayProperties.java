package com.simple.core.alipay;

import com.simple.core.SimplePayProperties;

/**
 * Created by Jin.Z.J 2020/11/25
 */
public class AliPayProperties extends SimplePayProperties {

    private Long id;

    private String appId;
    private String privateKey;
    private String aliPayPublicKey;

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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public void setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
    }

}
