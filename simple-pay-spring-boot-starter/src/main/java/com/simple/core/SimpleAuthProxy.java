package com.simple.core;

import com.simple.enums.PayMethod;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;

/**
 * Created by Jin.Z.J  2021/2/8
 */
public class SimpleAuthProxy implements SimpleAuth {

    private String appType;

    private SimplePayMethodFactory methodFactory;

    public SimpleAuthProxy(String appType, SimplePayMethodFactory methodFactory) {
        this.appType = appType;
        this.methodFactory = methodFactory;
    }

    @Override
    public <R> R accessToken(SimplePayParam<R> param) throws SimplePayException {
        return getSimpleAuth(param.method()).accessToken(param);
    }

    @Override
    public <R> R getResource(SimplePayParam<R> param) throws SimplePayException {
        return getSimpleAuth(param.method()).getResource(param);
    }

    private SimpleAuth getSimpleAuth(PayMethod method) throws SimplePayException{
        SimplePayFactory simplePayFactory = methodFactory.getFactory(method);
        SimpleAuth simpleAuth = simplePayFactory.getSimpleAuth(this.appType);
        if(simpleAuth == null){
            throw new SimplePayException("Non-existent appType %s",this.appType);
        }
        return simpleAuth;
    }


}