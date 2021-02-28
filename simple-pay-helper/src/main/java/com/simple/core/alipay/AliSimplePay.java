package com.simple.core.alipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.simple.core.AbstractSimplePay;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;

import java.util.Map;

/**
 *
 * Created by Jin.Z.J  2020/11/25
 */
public abstract class AliSimplePay extends AbstractSimplePay {

    private AlipayClient alipayClient;

    private AliPayProperties properties;

    public AliSimplePay(AlipayClient alipayClient, AliPayProperties properties) {
        this.alipayClient = alipayClient;
        this.properties = properties;
    }


    @Override
    public <R> R queryTradeOrder(SimplePayParam<R> param) throws SimplePayException {
        Map<String, Object> map = getBizContent(param);
        map.put("trade_no", null);
        try {
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
            String bizContent = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
            request.setBizContent(bizContent);//设置业务参数
            return (R) alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new SimplePayException(e.getErrMsg());
        }
    }

    @Override
    public <R> R closeOrder(SimplePayParam<R> param) throws SimplePayException {
        Map<String, Object> map = getBizContent(param);
        try {
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();//创建API对应的request类
            request.setBizContent(JSONObject.toJSONString(map));//设置业务参数
            return (R) alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new SimplePayException(e.getErrMsg());
        }
    }

    @Override
    public <R> R refund(SimplePayParam<R> param) throws SimplePayException {
        Map<String, Object> map = getBizContent(param);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();//创建API对应的request类
        request.setNotifyUrl((String) map.remove("notify_url"));
        request.setBizContent(JSONObject.toJSONString(map));//设置业务参数
        try {
            //通过alipayClient调用API，获得对应的response类
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            return (R) response;
        } catch (AlipayApiException e) {
            throw new SimplePayException(e.getErrMsg());
        }
    }

    @Override
    public <R> R queryRefund(SimplePayParam<R> param) throws SimplePayException {
        Map<String, Object> map = getBizContent(param);
        AlipayTradeFastpayRefundQueryRequest refundQueryRequest = new AlipayTradeFastpayRefundQueryRequest();
        refundQueryRequest.setBizContent(JSONObject.toJSONString(map));
        try {
            return (R) alipayClient.execute(refundQueryRequest);
        } catch (AlipayApiException e) {
            throw new SimplePayException(e.getErrMsg());
        }
    }


    @Override
    public Map<String,Object> getBizContent(SimplePayParam<?> param){
        Map<String, Object> map = super.getBizContent(param);
        String terminal =  (String)map.remove("terminal");
        return map;
    }




}
