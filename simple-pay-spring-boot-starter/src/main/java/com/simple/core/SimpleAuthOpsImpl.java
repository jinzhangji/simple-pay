package com.simple.core;

import com.simple.consts.AppConst;
import com.simple.enums.PayMethod;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public class SimpleAuthOpsImpl implements SimpleAuthOps {

    private SimplePayMethodFactory factory;

    public SimpleAuthOpsImpl(SimplePayMethodFactory factory) {
        this.factory = factory;
    }

    @Override
    public SimpleAuth auth(String appType) {
        return new SimpleAuthProxy(appType,factory);
    }

    @Override
    public SimpleAuth mp(){
        return auth(AppConst.MP);
    }

    @Override
    public SimpleAuth website(){
        return auth(AppConst.WEBSITE);
    }

    @Override
    public SimpleAuth oa(){
        return auth(AppConst.OA);
    }

    @Override
    public SimpleAuth apples(){
        return auth(AppConst.APPLES);
    }

}
