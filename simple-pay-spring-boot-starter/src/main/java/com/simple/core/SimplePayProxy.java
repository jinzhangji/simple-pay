package com.simple.core;

import com.simple.enums.PayMethod;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;

/**
 * 支付代理
 */
public class SimplePayProxy implements SimplePay {

    private String terminal;

    private SimplePayMethodFactory methodFactory;

    public SimplePayProxy(String terminal, SimplePayMethodFactory methodFactory) {
        this.terminal = terminal;
        this.methodFactory = methodFactory;
    }

    @Override
    public <R> R queryTradeOrder(SimplePayParam<R> param) throws SimplePayException {
        return getSimplePay(param.method(),param.propertiesId()).queryTradeOrder(param);
    }

    @Override
    public <R> R closeOrder(SimplePayParam<R> param) throws SimplePayException {
        return getSimplePay(param.method(),param.propertiesId()).closeOrder(param);
    }

    @Override
    public <R> R refund(SimplePayParam<R> param) throws SimplePayException {
        return getSimplePay(param.method(),param.propertiesId()).refund(param);
    }

    @Override
    public <R> R queryRefund(SimplePayParam<R> param) throws SimplePayException {
        return getSimplePay(param.method(),param.propertiesId()).queryRefund(param);
    }

    @Override
    public <R> R unifiedOrder(SimplePayParam<R> param) throws SimplePayException {
        return getSimplePay(param.method(),param.propertiesId()).unifiedOrder(param);
    }

    private SimplePay getSimplePay(PayMethod method,Long propertiesId){
        return methodFactory.getFactory(method).getSimplePay(terminal,propertiesId);
    }

}
