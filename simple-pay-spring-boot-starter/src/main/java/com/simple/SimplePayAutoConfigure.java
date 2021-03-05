package com.simple;

import com.simple.core.wechat.WeChatPayProperties;
import com.simple.enums.PayMethod;
import com.simple.properties.StatSimplePayProperties;
import com.simple.core.*;
import com.simple.service.PropertiesService;
import com.simple.service.SimplePayPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Jin.Z.J  2021/2/5
 */
@Configuration
@ConditionalOnClass({SimplePay.class})
@AutoConfigureBefore(SimplePayPropertiesService.class)
@EnableConfigurationProperties(StatSimplePayProperties.class)
@Import(SimplePayPropertiesService.class)
public class SimplePayAutoConfigure {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimplePayAutoConfigure.class);


    @Bean
    @ConditionalOnMissingBean
    public SimplePayTemplate simplePayTemplate() {
        LOGGER.info("Init SimplePayTemplate");
        return new SimplePayTemplate();
    }


    @Autowired
    private PropertiesService propertiesService;

    @Bean
    public SimplePayMethodFactory getSimplePayFactory(){

        PropertiesFactory weChatPropertiesFactory = new PropertiesFactory() {
            @Override
            public SimplePayProperties getProperties(String appType) {
                return propertiesService.getWeChatProperties(appType);
            }

            @Override
            public SimplePayProperties getProperties(Long id) {
                return propertiesService.getWeChatProperties(id);
            }
        };

        PropertiesFactory aliPropertiesFactory = new PropertiesFactory() {
            @Override
            public SimplePayProperties getProperties(String appType) {
                return propertiesService.getAliPayProperties(appType);
            }

            @Override
            public SimplePayProperties getProperties(Long id) {
                return propertiesService.getAliPayProperties(id);
            }
        };

        return new SimplePayMethodFactory(new AbstractPropertiesFactory() {
            @Override
            public PropertiesFactory getFactory(PayMethod method) {
                if(method.equals(PayMethod.WECHAT)){
                    return weChatPropertiesFactory;
                }else{
                    return aliPropertiesFactory;
                }
            }
        });
    }





}
