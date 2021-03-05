package com.simple.core.wechat;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.XmlUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.simple.core.AbstractSimplePay;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;
import com.simple.param.SimplePays;
import com.simple.result.wechatpay.WeChatUnifiedOrderResult;
import com.simple.utils.BeanUtils;
import com.simple.utils.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付
 * Created by Jin.Z.J  2020/11/25
 */
public abstract class WeChatSimplePay extends AbstractSimplePay {


    protected interface Consumer {

        void accept(Map<String, Object> map) throws SimplePayException;

    }


    protected <R> WeChatUnifiedOrderResult submitUnifiedOrder(SimplePayParam<R> payParam, Consumer consumer) throws SimplePayException {
        Map<String, Object> beanMap = getBizContent(payParam);
        Long orderId = (Long) beanMap.remove("order_id");
        String outTradeNo = (String) beanMap.get("out_trade_no");
        if (StringUtils.isEmpty(outTradeNo)) {
            throw new SimplePayException("out_trade_no can not be null");
        }
        String notifyUrl = (String) beanMap.get("notify_url");
        if (StringUtils.isEmpty(notifyUrl)) {
            notifyUrl = this.getProperties().getNotifyUrl();
            if (StringUtils.isNotEmpty(notifyUrl)) {
                beanMap.put("notify_url", notifyUrl);
            }
        }
        consumer.accept(beanMap);
        //参数签名
        beanMap.put("sign", this.getSign(beanMap));
        String str = this.submitPost(payParam.requestURI(), beanMap);
        try {
            WeChatUnifiedOrderResult result = xmlParseObject(str, WeChatUnifiedOrderResult.class);
            if (result.isSuccess()) {
                Map<String, Object> resMap = new HashMap<>();
                resMap.put("orderId", orderId);
                resMap.put("orderNo", outTradeNo);
                result.setMoreRes(resMap);
            }
            result.setProperties_id(getProperties().getId());
            result.setApiRes(str);
            return result;
        } catch (Exception e) {
            throw new SimplePayException(e);
        }
    }


    /**
     * 提交post 请求
     *
     * @param url
     * @param beanMap
     * @return
     * @throws SimplePayException
     */
    private String submitPost(String url, Map<String, Object> beanMap) throws SimplePayException {
        try (CloseableHttpClient httpclient = HttpClients.custom().build()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "text/xml");
            httpPost.setEntity(new StringEntity(getXmlParams(beanMap), "UTF-8"));
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw new SimplePayException(e);
        }
    }


    /**
     * 交易查询
     *
     * @param param
     * @param <R>
     * @return
     * @throws SimplePayException
     */
    public <R> R queryTradeOrder(SimplePayParam<R> param) throws SimplePayException {
        try {
            Map<String, Object> paramMap = getBizContent(param);
            paramMap.put("mch_id", this.getProperties().getMchid());
            paramMap.put("appid", this.appId());
            paramMap.put("nonce_str", IdUtil.simpleUUID().toUpperCase());
            paramMap.put("sign", this.getSign(paramMap));
            String resXml = submitPost(param.requestURI(), paramMap);
            return result(resXml, param.resClass());
        } catch (Exception e) {
            throw new SimplePayException(e);
        }
    }


    private <R> R result(String resXml, Class<R> resClass) throws Exception {
        R result = xmlParseObject(resXml, resClass);
        Map<String, Object> map = XmlUtil.xmlToMap(resXml);

        BeanUtils.foreach(resClass, prop -> {
            try {
                String name = prop.getField().getName();
                if ("moreMap".equals(name)) {
                    prop.setter().invoke(result, map);
                    return;
                } else if ("apiRes".equals(name)) {
                    prop.setter().invoke(result, resXml);
                }
                map.remove(name);
            } catch (Exception e) {
            }
        });
        return result;
    }


    /**
     * 关闭订单
     * @param param
     * @param <R>
     * @return
     * @throws SimplePayException
     */
    @Override
    public <R> R closeOrder(SimplePayParam<R> param) throws SimplePayException {
        try {
            Map<String, Object> map = getBizContent(param);
            map.put("mch_id", this.getProperties().getMchid());
            map.put("appid", this.appId());
            map.put("nonce_str", IdUtil.simpleUUID().toUpperCase());
            map.put("sign", this.getSign(map));
            String res = submitPost(param.requestURI(), map);
            return result(res, param.resClass());
        } catch (Exception e) {
            throw new SimplePayException(e);
        }
    }


    /**
     * 退款
     *
     * @param param
     * @param <R>
     * @return
     * @throws SimplePayException
     */
    @Override
    public <R> R refund(SimplePayParam<R> param) throws SimplePayException {
        Map<String, Object> map = getBizContent(param);
        String mchid = this.getProperties().getMchid();
        map.put("appid", this.appId());
        map.put("mch_id", mchid);
        map.put("nonce_str", IdUtil.simpleUUID());
        String notify_url = (String) map.get("notify_url");
        if (StringUtils.isEmpty(notify_url)) {
            map.put("notify_url", this.getProperties().getRefundNotifyUrl());
        }
        map.put("sign", getSign(map));
        SSLConnectionSocketFactory ssl;
        InputStream inputStream = null;
        try {
            String p12Path = this.getProperties().getPk12Path();
            if(p12Path.startsWith("classpath:")){
                String filename = p12Path.split(":")[1];
                inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
            }else{
                inputStream = new FileInputStream(this.getProperties().getPk12Path());
            }
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(inputStream, mchid.toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchid.toCharArray()).build();
            ssl = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        } catch (Exception e) {
            throw new SimplePayException(e);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        try (CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(ssl).build()) {
            HttpPost httpPost = new HttpPost(param.requestURI());
            httpPost.setEntity(new StringEntity(getXmlParams(map), "UTF-8"));
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                String xmlResult = EntityUtils.toString(response.getEntity(), "UTF-8");
                return result(xmlResult, param.resClass());
            }
        } catch (Exception e) {
            throw new SimplePayException(e);
        }
    }

    /**
     * appId
     *
     * @return
     */
    protected abstract String appId();


//    /**
//     * 凭证
//     * @return
//     */
//    protected abstract String secret();


    /**
     * 配置文件
     *
     * @return
     */
    protected abstract WeChatPayProperties getProperties();


    protected <T> String getSign(T bean) {
        return SimplePays.WeChat.getSign(bean, this.getProperties().getSignKey());
    }


    protected <T> String getXmlParams(T param) throws Exception {
        String xmlParam = new XmlMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writer()
                .withRootName("xml")
                .writeValueAsString(param);
        return xmlParam;
    }


    protected <T> T xmlParseObject(String xml, Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new XmlMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(xml, clazz);
    }


    @Override
    public <R> R queryRefund(SimplePayParam<R> param) throws SimplePayException {
        try {
            Map<String, Object> map = getBizContent(param);
            String mchid = this.getProperties().getMchid();
            map.put("appid", this.appId());
            map.put("mch_id", mchid);
            map.put("nonce_str", IdUtil.simpleUUID());
            map.put("sign", getSign(map));
            String res = submitPost(param.requestURI(), map);
            return result(res, param.resClass());
        } catch (Exception e) {
            throw new SimplePayException(e);
        }
    }

}
