package com.simple.core;

import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;

/**
 * Created by Jin.Z.J  2021/2/8
 */
public interface SimpleAuth extends ISimple{

    /**
     * 授权访问令牌
     * @param param
     * @param <R>
     * @return
     * @throws SimplePayException
     */
    <R> R accessToken(SimplePayParam<R> param) throws SimplePayException;


    /**
     * 获取资源
     * @param param
     * @param <R>
     * @return
     * @throws SimplePayException
     */
    <R> R getResource(SimplePayParam<R> param) throws SimplePayException;



}
