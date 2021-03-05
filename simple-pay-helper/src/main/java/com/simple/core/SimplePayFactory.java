package com.simple.core;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public interface SimplePayFactory {


    SimplePay getSimplePay(String terminal);

    SimplePay getSimplePay(String terminal,Long propertiesId);

    SimpleAuth getSimpleAuth(String appType);

    SimpleAuth getSimpleAuth(String appType,Long propertiesId);

}
