package com.simple.result.alipay;

import com.alipay.api.AlipayResponse;
import com.simple.annotation.Exclude;
import com.simple.result.SimplePayResult;
import com.simple.utils.BeanUtils;

import java.util.Map;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class AliPayUnifiedOrderResult implements SimplePayResult {

    private Long orderId;
    private String orderNo;
    @Exclude
    private AlipayResponse response;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public AlipayResponse getResponse() {
        return response;
    }

    public void setResponse(AlipayResponse response) {
        this.response = response;
    }

    public Map<String,Object> getResData(){
        Map<String,Object> map = BeanUtils.beanToMap(this);
        map.put("body",response.getBody());
        return map;
    }

    @Override
    public boolean isSuccess() {
        return this.response != null && response.isSuccess();
    }
}
