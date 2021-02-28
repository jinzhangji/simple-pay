package com.simple.core;

import com.simple.enums.PayMethod;

/**
 * 支付终端
 */
public interface SimplePayOps {


    /**
     * 指定终端支付
     * @param terminal
     * @return
     */
    SimplePay terminal(String terminal);

    /**
     * h5支付
     * @return
     */
    SimplePay h5();

    /**
     * app支付
     * @return
     */
    SimplePay app();

    /**
     *
     * 获取PC
     * @return
     */
    SimplePay pc();

    /**
     * 微信公众号支付
     * @return
     */
    SimplePay wpp();


    /**
     * 小程序支付
     * @return
     */
    SimplePay applets();


    /**
     * 根据支付方式和终端获取
     * @param method   支付方式
     * @param terminal 终端
     * @return
     */
    SimplePay getSimplePay(PayMethod method, String terminal);

    /**
     * 获取微信指定终端
     * @param terminal
     * @return
     */
    SimplePay wechatPay(String terminal);


    /**
     * 获取支付宝指定终端
     * @param terminal
     * @return
     */
    SimplePay aliPay(String terminal);



}
