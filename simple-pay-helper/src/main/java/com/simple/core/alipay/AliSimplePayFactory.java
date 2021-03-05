package com.simple.core.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.simple.consts.AppConst;
import com.simple.consts.TerminalConst;
import com.simple.core.*;
import com.simple.enums.AliTerminalType;
import com.simple.utils.ObjCacheUtils;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class AliSimplePayFactory implements SimplePayFactory {

    private static final String ALIPAY_SERVER_URL = "https://openapi.alipay.com/gateway.do";

    private static final String ALIPAY_FORMAT = "json";

    private static final String ALIPAY_SIGN_TYPE = "RSA2";

    public PropertiesFactory propertiesFactory;

    public AliSimplePayFactory(PropertiesFactory propertiesFactory) {
        this.propertiesFactory = propertiesFactory;
    }

    @Override
    public SimplePay getSimplePay(String terminal) {
        return getSimplePay(terminal,null);
    }

    @Override
    public SimplePay getSimplePay(String terminal, Long propertiesId) {
        AliPayProperties properties = getProperties(AliTerminalType.getAppType(terminal),propertiesId);
        if(properties == null){
            throw new NullPointerException("No properties");
        }
        String key = new StringBuilder("alipay")
                .append("-").append(terminal)
                .append("-").append(properties.getAppId())
                .toString();
        return (SimplePay) ObjCacheUtils.get(key,() -> {
            AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_SERVER_URL
                    , properties.getAppId()
                    , properties.getPrivateKey()
                    , ALIPAY_FORMAT
                    , "UTF-8"
                    ,properties.getAliPayPublicKey()
                    , ALIPAY_SIGN_TYPE);

            if(terminal.equalsIgnoreCase(TerminalConst.APP)){
                return new AliSimplePayApp(alipayClient,properties);
            }else if(terminal.equalsIgnoreCase(TerminalConst.H5)){
                return new AliSimplePayWap(alipayClient,properties);
            }else if(terminal.equalsIgnoreCase(TerminalConst.PC)){
                return new AliSimplePayPc(alipayClient,properties);
            }else if(terminal.equalsIgnoreCase(TerminalConst.APPLETS)){
                return new AliSimplePayApplets(alipayClient,properties);
            }else{
                return null;
            }
        });
    }


    @Override
    public SimpleAuth getSimpleAuth(String appType) {
        return getSimpleAuth(appType,null);
    }

    @Override
    public SimpleAuth getSimpleAuth(String appType, Long propertiesId) {
        AliPayProperties properties = getProperties(appType,propertiesId);
        if(properties == null){
            throw new NullPointerException(appType + " not properties");
        }
        String key = new StringBuilder("aliauth")
                .append("-").append(appType)
                .append("-").append(properties.getAppId())
                .toString();
        return (SimpleAuth) ObjCacheUtils.get(key,() -> {
            AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_SERVER_URL
                    , properties.getAppId()
                    , properties.getPrivateKey()
                    , ALIPAY_FORMAT
                    , "UTF-8"
                    ,properties.getAliPayPublicKey()
                    , ALIPAY_SIGN_TYPE);
            if(AppConst.APPLES.equalsIgnoreCase(appType)) {
                return new AliSimpleAuth(alipayClient);
            }else{
                return new AliSimpleAuth(alipayClient);
            }
        });
    }


    private AliPayProperties getProperties(String appType, Long propertiesId){
        SimplePayProperties properties;
        if(propertiesId != null){
            properties = propertiesFactory.getProperties(propertiesId);
        }else{
            properties = propertiesFactory.getProperties(appType);
        }
        return (AliPayProperties) properties;
    }


}
