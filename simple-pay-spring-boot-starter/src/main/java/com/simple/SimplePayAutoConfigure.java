package com.simple;

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

        return new SimplePayMethodFactory(new ExtPropertiesFactory() {
            @Override
            public SimplePayProperties getProperties(Long id) {
                return propertiesService.getWeChatProperties(id);
            }

            @Override
            public SimplePayProperties getProperties(String appType) {
                return propertiesService.getWeChatProperties(appType);
            }
        }, new ExtPropertiesFactory() {

            @Override
            public SimplePayProperties getProperties(Long id) {
                return propertiesService.getAliPayProperties(id);
            }

            @Override
            public SimplePayProperties getProperties(String appType) {
                return propertiesService.getAliPayProperties(appType);
            }
        });
    }

}
