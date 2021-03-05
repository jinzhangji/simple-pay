package com.simple.core.wechat;

import cn.hutool.core.util.IdUtil;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;
import com.simple.result.wechatpay.WeChatUnifiedOrderResult;
import com.simple.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信端公众号内支付
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatSimplePayWpp extends WeChatSimplePay {

    private static final String TRADE_TYPE = "JSAPI";

    private WeChatPayProperties properties;

    public WeChatSimplePayWpp(WeChatPayProperties properties) {
        this.properties = properties;
    }

    @Override
    public <R> R unifiedOrder(SimplePayParam<R> param) throws SimplePayException {

        WeChatUnifiedOrderResult result = super.submitUnifiedOrder(param, paramMap -> {
            String openId = (String)paramMap.get("openid");
            if(StringUtils.isEmpty(openId)){
                throw new SimplePayException("param [openid] Can not be empty");
            }
            paramMap.put("appid",this.appId());
            paramMap.put("mch_id",this.properties.getMchid());
            paramMap.put("trade_type",TRADE_TYPE);
        });

        if(result.isSuccess()){
            String nonceStr = IdUtil.simpleUUID().toUpperCase();
            long timestamp = System.currentTimeMillis() / 1000;
            String pkg = "prepay_id="+result.getPrepay_id();
            Map<String,Object> map = new HashMap<>();
            map.put("timestamp",timestamp);
            map.put("nonceStr",nonceStr);
            map.put("package",pkg);
            map.put("signType","MD5");
            map.put("paySign",this.getSign(this.appId(),timestamp,nonceStr,pkg));
            result.getMoreRes().put("body",map);
        }
        return (R)result;
    }


    private String getSign(String appId,long timestamp,String nonceStr,String pkg){
        Map<String,Object> signMap = new HashMap<>();
        signMap.put("appId",appId);
        signMap.put("timeStamp",timestamp);
        signMap.put("nonceStr",nonceStr);
        signMap.put("package",pkg);
        signMap.put("signType","MD5");
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
