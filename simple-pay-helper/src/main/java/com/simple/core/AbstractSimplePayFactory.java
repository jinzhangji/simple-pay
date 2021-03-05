package com.simple.core;

import com.simple.enums.PayMethod;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public abstract class AbstractSimplePayFactory{


    public abstract SimplePayFactory getFactory(PayMethod method);

}
