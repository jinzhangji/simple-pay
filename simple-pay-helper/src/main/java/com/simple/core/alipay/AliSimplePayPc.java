package com.simple.core.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;
import com.simple.result.alipay.AliPayUnifiedOrderResult;
import com.simple.utils.StringUtils;

import java.util.Map;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class AliSimplePayPc extends AliSimplePay {

    private AlipayClient alipayClient;

    private AliPayProperties properties;

    public AliSimplePayPc(AlipayClient alipayClient, AliPayProperties properties) {
        super(alipayClient,properties);
        this.alipayClient = alipayClient;
        this.properties = properties;
    }


    @Override
    public <R> R unifiedOrder(SimplePayParam<R> param) throws SimplePayException {
        Map<String, Object> map = getBizContent(param);
        //订单主键id
        Long orderId = (Long) map.remove("orderId");
        //订单号
        String orderNo = (String) map.get("out_trade_no");
        //回调通知地址
        String notifyUrl = (String) map.remove("notify_url");
        if(StringUtils.isEmpty(notifyUrl)){
            notifyUrl = this.properties.getNotifyUrl();
        }
        //回调页面
        String returnUrl = (String) map.remove("return_url");
        try{
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setNotifyUrl(notifyUrl);
            request.setReturnUrl(returnUrl);
            request.setBizContent(JSONObject.toJSONString(map));
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            AliPayUnifiedOrderResult result = new AliPayUnifiedOrderResult();
            result.setOrderNo(orderNo);
            result.setResponse(response);
            result.setOrderId(orderId);
            return (R) result;
        }catch (AlipayApiException e){
            throw new SimplePayException(e.getErrCode() + ":" + e.getErrCode());
        }
    }

}
