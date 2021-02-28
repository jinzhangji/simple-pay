package com.simple.core;

import com.simple.core.alipay.AliSimplePayFactory;
import com.simple.core.wechat.WeChatSimplePayFactory;
import com.simple.enums.PayMethod;

import java.util.Objects;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class SimplePayMethodFactory extends AbstractSimplePayFactory{

    private final ExtPropertiesFactory wechatPropertiesFactory;

    private final ExtPropertiesFactory aliPropertiesFactory;

    public SimplePayMethodFactory(ExtPropertiesFactory wechatPropertiesFactory, ExtPropertiesFactory aliPropertiesFactory) {
        this.wechatPropertiesFactory = wechatPropertiesFactory;
        this.aliPropertiesFactory = aliPropertiesFactory;
    }

    @Override
    public SimplePayFactory getFactory(PayMethod method) {
        return getFactory(method,null);
    }


    @Override
    public SimplePayFactory getFactory(PayMethod method, Long propertiesId) {
        Objects.requireNonNull(method,"pay method can not be null");
        if(method.equals(PayMethod.WECHAT)){
            return new WeChatSimplePayFactory((appType) -> {
                if(propertiesId != null){
                    return wechatPropertiesFactory.getProperties(propertiesId);
                }else{
                    return wechatPropertiesFactory.getProperties(appType);
                }
            });
        }else if(method.equals(PayMethod.ALI)){
            return new AliSimplePayFactory((appType) -> {
                if(propertiesId != null){
                    return aliPropertiesFactory.getProperties(propertiesId);
                }else{
                    return aliPropertiesFactory.getProperties(appType);
                }
            });
        }else{
            throw new NullPointerException("Non-SimplePayFactory");
        }
    }



}
