package com.simple.core.wechat;

import cn.hutool.core.util.IdUtil;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;
import com.simple.result.wechatpay.WeChatUnifiedOrderResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信app端支付
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatSimplePayApp extends WeChatSimplePay {

    private static final String TRADE_TYPE = "APP";

    private WeChatPayProperties properties;

    public WeChatSimplePayApp(WeChatPayProperties properties) {
        this.properties = properties;
    }

    @Override
    public <R> R unifiedOrder(SimplePayParam<R> param) throws SimplePayException {

        WeChatUnifiedOrderResult result = super.submitUnifiedOrder(param,(beanMap) -> {
            beanMap.put("appid",this.appId());
            beanMap.put("mch_id",this.properties.getMchid());
            beanMap.put("trade_type",TRADE_TYPE);
            beanMap.remove("openid");

        });

        if(result.isSuccess()){
            long timestamp = System.currentTimeMillis() / 1000;
            String nonceStr = IdUtil.simpleUUID().toUpperCase();
            String pkg = "" +
                    "sign=WXPay";
            Map<String,Object> map = new HashMap<>();
            map.put("appid",result.getAppid());
            map.put("partnerid",result.getMch_id());
            map.put("perpayid",result.getPrepay_id());
            map.put("package",pkg);
            map.put("noncestr",nonceStr);
            map.put("timestamp",timestamp);
            map.put("sign",this.getSign(result,timestamp,nonceStr,pkg));
            result.getMoreRes().put("body",map);
        }
        return (R)result;
    }


    private String getSign(WeChatUnifiedOrderResult result, long timestamp, String nonceStr, String pkg){
        Map<String,Object> signMap = new HashMap<>();
        signMap.put("appid",result.getAppid());
        signMap.put("timestamp",timestamp);
        signMap.put("prepayid",result.getPrepay_id());
        signMap.put("partnerid",result.getMch_id());
        signMap.put("package",pkg);
        signMap.put("noncestr",nonceStr);
        return super.getSign(signMap);
    }


    @Override
    protected String appId() {
        return properties.getAppId();
    }

    @Override
    protected WeChatPayProperties getProperties() {
        return this.properties;
    }
}
