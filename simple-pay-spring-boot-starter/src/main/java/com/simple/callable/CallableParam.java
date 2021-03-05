package com.simple.callable;

import com.simple.enums.PayMethod;

/**
 * 回调业务参数
 * Created by Jin.Z.J  2020/11/26
 */
public class CallableParam{
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 其他业务数据
     */
    private Object data;

    /**
     * 支付方式
     */
    private PayMethod method;


    public CallableParam(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PayMethod getMethod() {
        return method;
    }

    public void setMethod(PayMethod method) {
        this.method = method;
    }
}
