package com.simple.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 支付模板
 * Created by Jin.Z.J  2020/11/26
 */
@Component
public class SimplePayTemplate{

    @Autowired
    private SimplePayMethodFactory factory;

    public SimplePayOps pay(){
        return new SimplePayOpsImpl(factory);
    }

    public SimpleAuthOps auth(){
        return new SimpleAuthOpsImpl(factory);
    }



}
