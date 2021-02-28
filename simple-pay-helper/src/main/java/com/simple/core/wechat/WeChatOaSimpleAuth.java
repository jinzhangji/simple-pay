package com.simple.core.wechat;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public class WeChatOaSimpleAuth extends WeChatSimpleAuth {

    private WeChatPayProperties properties;

    public WeChatOaSimpleAuth(WeChatPayProperties properties) {
        super(properties);
        this.properties = properties;
    }


}
