package com.simple.param.alipay;

import com.simple.enums.PayMethod;
import com.simple.param.AbstractSimplePayParam;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public abstract class AliAbstractSimplePayParam<R> extends AbstractSimplePayParam<R> {

    @Override
    public PayMethod method() {
        return PayMethod.ALI;
    }

}
