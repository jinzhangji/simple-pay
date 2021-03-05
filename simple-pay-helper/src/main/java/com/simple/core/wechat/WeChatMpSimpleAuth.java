package com.simple.core.wechat;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public class WeChatMpSimpleAuth extends WeChatSimpleAuth {

    private WeChatPayProperties properties;

    public WeChatMpSimpleAuth(WeChatPayProperties properties) {
        super(properties);
        this.properties = properties;
    }

}
