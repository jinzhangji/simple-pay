package com.simple.core;

import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;
/**
 * Created by Jin.Z.J  2020/11/25
 */
public interface SimplePay extends ISimple{


    <R> R unifiedOrder(SimplePayParam<R> param) throws SimplePayException;


    /**
     * 订单查询
     * @param param
     * @return
     * @throws SimplePayException
     */
    <R> R queryTradeOrder(SimplePayParam<R> param) throws SimplePayException;


    /**
     * 关闭订单
     * @param param
     * @return
     * @throws SimplePayException
     */
    <R> R closeOrder(SimplePayParam<R> param) throws SimplePayException;


    /**
     * 退款
     * @param param
     * @return
     * @throws SimplePayException
     */
    <R> R refund(SimplePayParam<R> param) throws SimplePayException;

    /**
     * 查询退款
     * @param param
     * @return
     * @throws SimplePayException
     */
    <R> R queryRefund(SimplePayParam<R> param) throws SimplePayException;




}
