package com.simple.core.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simple.core.SimpleAuth;
import com.simple.core.SimplePay;
import com.simple.exception.SimplePayException;
import com.simple.param.SimplePayParam;
import com.simple.param.wechatpay.WeChatAuthTicketParam;
import com.simple.result.wechatpay.WeChatAuthTicketResult;
import com.simple.utils.BeanUtils;
import com.simple.utils.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public abstract class WeChatSimpleAuth implements SimpleAuth {

    private WeChatPayProperties properties;

    public WeChatSimpleAuth(WeChatPayProperties properties) {
        this.properties = properties;
    }


    @Override
    public <R> R accessToken(SimplePayParam<R> param) throws SimplePayException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder uriBuilder = new URIBuilder(param.requestURI());
            uriBuilder.addParameter("appid", properties.getAppId());
            uriBuilder.addParameter("secret",properties.getSecret());
            Map<String,Object> map = getBizContent(param);
            if(map != null && !map.isEmpty()){
                map.forEach((k,v) -> uriBuilder.addParameter(k,v == null ? StringUtils.EMPYT : v.toString()));
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            try(CloseableHttpResponse response = httpClient.execute(httpGet)){
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String json = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObject = JSON.parseObject(json);
                    jsonObject.put("properties_id",properties.getId());
                    return result(json,jsonObject.toJavaObject(param.resClass()));
                } else {
                    throw new SimplePayException("http statusCode %s",String.valueOf(statusCode));
                }
            }
        } catch (Exception e) {
            throw new SimplePayException(e);
        }
    }


    @Override
    public <R> R getResource(SimplePayParam<R> param) throws SimplePayException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            URIBuilder uriBuilder = new URIBuilder(param.requestURI());
            uriBuilder.addParameter("lang","zh_CN");
            Map<String,Object> map = getBizContent(param);
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
                    return result(json,jsonObject.toJavaObject(param.resClass()));
                } else {
                    throw new SimplePayException("http statusCode %s",String.valueOf(statusCode));
                }
            }
        } catch (Exception e) {
            throw new SimplePayException(e);
        }
    }

    private <R> R result(String apiRes,R result) throws Exception {
        Map<String, Object> map = BeanUtils.beanToMap(result);
        BeanUtils.foreach(result.getClass(), prop -> {
            try {
                String name = prop.getField().getName();
                if ("moreMap".equals(name)) {
                    prop.setter().invoke(result, map);
                    return;
                } else if ("apiRes".equals(name)) {
                    prop.setter().invoke(result, apiRes);
                }
                map.remove(name);
            } catch (Exception e) {
            }
        });
        return result;
    }



}
