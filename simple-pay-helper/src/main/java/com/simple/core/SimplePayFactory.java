package com.simple.core;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public interface SimplePayFactory {


    SimplePay getSimplePay(String terminal);


    SimpleAuth getSimpleAuth(String appType);
}
