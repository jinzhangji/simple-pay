package com.simple.service;

import cn.hutool.core.collection.CollectionUtil;
import com.simple.core.SimplePayProperties;
import com.simple.core.alipay.AliPayProperties;
import com.simple.core.wechat.WeChatPayProperties;
import com.simple.properties.StatSimplePayProperties;
import com.simple.utils.BeanUtils;
import com.simple.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

@Component
public class SimplePayPropertiesService implements PropertiesService {

    @Autowired
    protected StatSimplePayProperties statProperties;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Map<Long,StatSimplePayProperties.StatWeChatPayProperties> weChatPayPropertiesMap;

    private Map<Long,StatSimplePayProperties.StatAliProperties> aliPayPropertiesMap;

    private ReentrantLock lock = new ReentrantLock();


    private static final String SELECT_CONFIG_BY_ID = "SELECT * FROM wechat_pay_config WHERE id = ?";

    private static final String SELECT_CONFIG_BY_APPTYPE = "SELECT * FROM wechat_pay_config WHERE app_type = ? AND state = 1 AND is_del = 0 ORDER BY rand() LIMIT 1";



    @Override
    public WeChatPayProperties getWeChatProperties(Long id) {
        WeChatPayProperties properties;
        if(statProperties.isWechatDb()){
            properties = jdbcTemplate.queryForObject(SELECT_CONFIG_BY_ID,new BeanPropertyRowMapper<>(WeChatPayProperties.class),id);
        }else{
            if(this.weChatPayPropertiesMap == null){
                lock.lock();
                try{
                    if(this.weChatPayPropertiesMap == null){
                        this.weChatPayPropertiesMap = new HashMap<>();
                        statProperties.getWechat()
                                .values()
                                .stream().flatMap(list -> list.stream())
                                .forEach(p ->  weChatPayPropertiesMap.put(p.getId(),p));
                    }
                }finally {
                    lock.unlock();
                }
            }
            properties = weChatPayPropertiesMap.get(id);
        }
        setDefaultProp(properties);
        return properties;
    }

    @Override
    public WeChatPayProperties getWeChatProperties(String appType) {
        WeChatPayProperties properties = null;
        if(statProperties.isWechatDb()){
            properties = jdbcTemplate.queryForObject(SELECT_CONFIG_BY_APPTYPE,new BeanPropertyRowMapper<>(WeChatPayProperties.class),appType);
        }else{
            List<StatSimplePayProperties.StatWeChatPayProperties> list = statProperties.getWechat().get(appType);
            if(CollectionUtil.isNotEmpty(list)){
                properties = list.get(new Random().nextInt(list.size()));
            }
        }
        setDefaultProp(properties);
        return properties;
    }

    @Override
    public AliPayProperties getAliPayProperties(Long id) {
        if(statProperties.isAliDb()){
            //pass
        }else{
            if(this.aliPayPropertiesMap == null){
                lock.lock();
                try{
                    if(this.aliPayPropertiesMap == null){
                        this.aliPayPropertiesMap = new HashMap<>();
                        statProperties.getAli().values().stream()
                                .flatMap(list -> list.stream())
                                .forEach(p ->  aliPayPropertiesMap.put(p.getId(),p));
                    }
                }finally {
                    lock.unlock();
                }
            }
            AliPayProperties properties = aliPayPropertiesMap.get(id);
            setDefaultProp(properties);
            return properties;
        }

        return null;
    }

    @Override
    public AliPayProperties getAliPayProperties(String appType) {
        AliPayProperties properties = null;
        if(statProperties.isAliDb()){
            //pass
        }else{
            List<StatSimplePayProperties.StatAliProperties> list = statProperties.getAli().get(appType);
            if(CollectionUtil.isNotEmpty(list)){
                properties = list.get(new Random().nextInt(list.size()));
            }
        }
        setDefaultProp(properties);
        return properties;
    }



    /**
     * 设置默认配置
     * @param properties
     */
    protected void setDefaultProp(SimplePayProperties properties){
        if(properties == null){
            return;
        }
        if(StringUtils.isEmpty(properties.getNotifyUrl())){
            properties.setNotifyUrl(statProperties.getNotifyUrl());
        }
        if(StringUtils.isEmpty(properties.getRefundNotifyUrl())){
            properties.setRefundNotifyUrl(statProperties.getRefundNotifyUrl());
        }
        if(StringUtils.isEmpty(properties.getRedirectUrl())){
            properties.setRedirectUrl(statProperties.getRedirectUrl());
        }
        Map<String,Object> objMap = BeanUtils.beanToMap(properties);
        properties.setRefundNotifyUrl(formatUrl(properties.getRefundNotifyUrl(),objMap));
        properties.setNotifyUrl(formatUrl(properties.getNotifyUrl(),objMap));
    }


    /**
     * 格式化占位配置
     * @param tempNotifyUrl
     * @param map
     * @return
     */
    private String formatUrl(String tempNotifyUrl,Map<String,Object> map){
        if(StringUtils.isNotEmpty(tempNotifyUrl) && Pattern.matches(".*\\{([^}]*)\\}.*", tempNotifyUrl)){
            String arr[] = tempNotifyUrl.split("/");
            for (int i = 0; i < arr.length; i++) {
                String s = arr[i];
                if(s.startsWith("{") && s.endsWith("}")){
                    String attr = s.substring(1, s.length() - 1);
                    Object v = map.get(attr);
                    arr[i] = v == null ? "null" : v.toString();
                }
            }
            return String.join("/",arr);
        }
        return tempNotifyUrl;
    }

}
