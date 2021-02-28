package com.simple.param;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.simple.exception.SimplePayException;
import com.simple.param.alipay.AliPayCloseOrderParam;
import com.simple.param.alipay.AliPayQueryOrderParam;
import com.simple.param.alipay.AliPayRefundParam;
import com.simple.param.alipay.AliPayUnifiedOrderParam;
import com.simple.param.wechatpay.*;
import com.simple.result.wechatpay.WeChatAuthTicketResult;
import com.simple.utils.BeanUtils;
import com.simple.utils.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public abstract class SimplePays {

    private SimplePays(){

    }


    public final static class WeChat{

        public static WeChatPayUnifiedOrderParam createUnifiedOrderParam(){
            return new WeChatPayUnifiedOrderParam();
        }

        public static WeChatPayCloseOrderParam createCloseOrderParam(){
            return new WeChatPayCloseOrderParam();
        }

        public static WeChatPayQueryOrderParam createQueryOrderParam(){
            return new WeChatPayQueryOrderParam();
        }

        public static WeChatPayRefundParam createRefundParam(){
            return new WeChatPayRefundParam();
        }

        public static WeChatPayQueryRefundParam createQueryRefundParam(){
            return new WeChatPayQueryRefundParam();
        }


        public static WeChatAuthSnsAccessTokenParam createAuthAccessTokenParam(){
            return new WeChatAuthSnsAccessTokenParam();
        }

        public static WeChatAuthSnsUserInfoParam createAuthUserInfoParam(){
            return new WeChatAuthSnsUserInfoParam();
        }

        public static WeChatAuthTicketParam createAuthTicketParam(){
            return new WeChatAuthTicketParam();
        }


        public static WeChatAuthTicketResult getTicket(WeChatAuthTicketParam param) throws SimplePayException {
            try(CloseableHttpClient httpClient = HttpClients.createDefault()){
                URIBuilder uriBuilder = new URIBuilder(param.requestURI());
                Map<String,Object> map = BeanUtils.beanToMap(param);
                if(map != null && !map.isEmpty()){
                    map.forEach((k,v) -> uriBuilder.addParameter(k,v != null ? v.toString() : StringUtils.EMPYT));
                }
                HttpGet httpGet = new HttpGet(uriBuilder.build());
                try(CloseableHttpResponse response = httpClient.execute(httpGet)){
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String json = EntityUtils.toString(response.getEntity(),"UTF-8");
                        JSONObject jsonObject = JSON.parseObject(json);
                        jsonObject.put("properties_id",param.propertiesId());
                        return jsonObject.toJavaObject(param.resClass());
                    } else {
                        throw new SimplePayException("http statusCode %s",String.valueOf(statusCode));
                    }
                }
            } catch (Exception e) {
                throw new SimplePayException(e);
            }
        }


        /**
         * @param bean javaBean 和 Map
         * @param signKey 加密秘串
         * @param <T>
         * @return
         */
        public static <T> String getSign(T bean,String signKey) {
            Map<String,Object> paramMap;
            if(bean instanceof Map){
                paramMap = (Map<String,Object>)bean;
            }else{
                paramMap = BeanUtils.beanToMap(bean);
            }
            String str = MapUtil.sortJoin(paramMap,"&","=",true,null);
            StringBuilder sb = new StringBuilder(str).append("&key=").append(signKey);
            String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
            return sign;
        }


    }

    public final static class Ali{

        public static AliPayUnifiedOrderParam createUnifiedOrderParam(){
            return new AliPayUnifiedOrderParam();
        }

        public static AliPayCloseOrderParam createCloseOrderParam(){
            return new AliPayCloseOrderParam();
        }

        public static AliPayQueryOrderParam createQueryOrderParam(){
            return new AliPayQueryOrderParam();
        }

        public static AliPayRefundParam createRefundParam(){
            return new AliPayRefundParam();
        }


        public static boolean rsaCheckV1(Map<String,String> params,String publicKey) throws AlipayApiException {
            return rsaCheckV1(params, publicKey,"UTF-8");
        }

        public static boolean rsaCheckV1(Map<String,String> params,String publicKey,String charset) throws AlipayApiException {
            return rsaCheckV1(params, publicKey, charset, "RSA2");
        }

        public static boolean rsaCheckV1(Map<String,String> params,String publicKey,String charset,String signType) throws AlipayApiException {
            return AlipaySignature.rsaCheckV1(params, publicKey, charset, signType);
        }

    }



}
