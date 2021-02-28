package com.simple.param;

import com.simple.enums.PayMethod;


/**
 * Created by Jin.Z.J  2020/11/25
 */
public interface SimplePayParam<R> {

    /**
     * 支付方式
     * @return
     */
    PayMethod method();

    /**
     * 请求地址
     * @return
     */
    String requestURI();

    /**
     * 添加参数
     * @param key
     * @param value
     * @return
     */
    SimplePayParam<R> addParam(String key,Object value);

    /**
     * 返回值类型Class
     * @return
     */
    Class<R> resClass();


    Long propertiesId();

}
