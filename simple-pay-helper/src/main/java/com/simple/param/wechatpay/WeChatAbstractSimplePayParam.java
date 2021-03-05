package com.simple.param.wechatpay;

import com.simple.enums.PayMethod;
import com.simple.param.AbstractSimplePayParam;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public abstract class WeChatAbstractSimplePayParam<R> extends AbstractSimplePayParam<R> {

    @Override
    public PayMethod method() {
        return PayMethod.WECHAT;
    }

}
