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



}
