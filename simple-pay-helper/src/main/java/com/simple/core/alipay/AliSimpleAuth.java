package com.simple.core.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.simple.core.SimpleAuth;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;
import com.simple.param.alipay.AliOpenAuthTokenParam;
import com.simple.param.alipay.AliSystemOauthTokenParam;
import com.simple.utils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Jin.Z.J  2021/2/8
 */
public class AliSimpleAuth implements SimpleAuth {

    private AlipayClient alipayClient;

    public AliSimpleAuth(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    @Override
    public <R> R accessToken(SimplePayParam<R> param) throws SimplePayException{
        if(param instanceof AliSystemOauthTokenParam){
            return accessTokenLocal(param);
        }else if(param instanceof AliOpenAuthTokenParam){
            return accessTokenApp(param);
        }else{
            throw new SimplePayException("param type error!");
        }
    }


    private <R> R accessTokenLocal(SimplePayParam<R> param) throws SimplePayException{
        Map<String, Object> map = getBizContent(param);
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        BeanUtils.foreach(request.getClass(),(p) -> {
            Object v = map.get(p.getField().getName());
            if(v != null){
                try {
                    p.setter().invoke(request,v);
                } catch (IllegalAccessException | InvocationTargetException e) {
                }
            }
        });
        try {
            return (R) alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new SimplePayException("%s-%s",e.getErrCode(),e.getErrMsg());
        }
    }


    private <R> R accessTokenApp(SimplePayParam<R> param) throws SimplePayException {
        Map<String, Object> map = getBizContent(param);
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        request.setBizContent(JSONObject.toJSONString(map));
        try {
            return (R) alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new SimplePayException("%s-%s",e.getErrCode(),e.getErrMsg());
        }
    }

    @Override
    public <R> R getResource(SimplePayParam<R> param) throws SimplePayException {
        throw new SimplePayException("non-impl");
    }
}
