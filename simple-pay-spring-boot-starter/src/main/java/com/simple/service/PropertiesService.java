package com.simple.service;

import com.simple.core.alipay.AliPayProperties;
import com.simple.core.wechat.WeChatPayProperties;

public interface PropertiesService {



    WeChatPayProperties getWeChatProperties(Long id);


    WeChatPayProperties getWeChatProperties(String appType);


    AliPayProperties getAliPayProperties(Long id);


    AliPayProperties getAliPayProperties(String appType);




}
