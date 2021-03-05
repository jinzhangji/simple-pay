package com.simple.callable;

import com.simple.exception.SimplePayException;

/**
 * Created by Jin.Z.J  2020/11/26
 */
public interface SimplePayCallable{


    /**
     * 支付回调所触发的方法
     * @param callableParam
     * @return
     * @throws SimplePayException
     */
    CallableResult payCall(CallableParam callableParam) throws SimplePayException;

    /**
     * 退款回调所触发的方法
     * @param callableParam
     * @return
     * @throws SimplePayException
     */
    CallableResult refundCall(CallableParam callableParam) throws SimplePayException;

    /**
     * 交易查询所触发的方法
     * @param callableParam
     * @return
     * @throws SimplePayException
     */
    CallableResult queryTrade(CallableParam callableParam) throws SimplePayException;

}
