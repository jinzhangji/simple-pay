package com.simple.core;

import com.simple.core.alipay.AliSimplePayFactory;
import com.simple.core.wechat.WeChatSimplePayFactory;
import com.simple.enums.PayMethod;

import java.util.Objects;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class SimplePayMethodFactory extends AbstractSimplePayFactory{

    private AbstractPropertiesFactory factory;

    public SimplePayMethodFactory(AbstractPropertiesFactory factory) {
        this.factory = factory;
    }

    @Override
    public SimplePayFactory getFactory(PayMethod method) {
        Objects.requireNonNull(method,"pay method can not be null");
        PropertiesFactory propertiesFactory = factory.getFactory(method);
        if(method.equals(PayMethod.WECHAT)){
            return new WeChatSimplePayFactory(propertiesFactory);
        }else if(method.equals(PayMethod.ALI)){
            return new AliSimplePayFactory(propertiesFactory);
        }else{
            throw new NullPointerException("Non-SimplePayFactory");
        }
    }


}
