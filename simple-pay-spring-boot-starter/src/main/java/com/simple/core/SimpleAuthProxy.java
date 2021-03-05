package com.simple.core;

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
        return methodFactory.getFactory(param.method()).getSimpleAuth(appType,param.propertiesId()).accessToken(param);
    }

    @Override
    public <R> R getResource(SimplePayParam<R> param) throws SimplePayException {
        return methodFactory.getFactory(param.method()).getSimpleAuth(appType,param.propertiesId()).getResource(param);
    }



}