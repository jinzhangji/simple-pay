package com.simple.core.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;
import com.simple.result.alipay.AliPayUnifiedOrderResult;
import com.simple.utils.StringUtils;

import java.util.Map;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class AliSimplePayWap extends AliSimplePay {

    private AlipayClient alipayClient;

    private AliPayProperties properties;

    public AliSimplePayWap(AlipayClient alipayClient, AliPayProperties properties) {
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
        String returnUrl = (String) map.remove("return_url");
        String notifyUrl = (String) map.remove("notify_url");
        if(StringUtils.isEmpty(returnUrl)){
            returnUrl = this.properties.getRedirectUrl();
        }
        if(StringUtils.isEmpty(notifyUrl)){
            notifyUrl = this.properties.getNotifyUrl();
        }
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        request.setBizContent(JSONObject.toJSONString(map));
        try{
            AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
            AliPayUnifiedOrderResult result = new AliPayUnifiedOrderResult();
            result.setOrderId(orderId);
            result.setOrderNo(orderNo);
            result.setResponse(response);
            return (R) result;
        }catch (AlipayApiException e){
            throw new SimplePayException(e.getErrCode() + ":" + e.getErrMsg());
        }
    }

}
