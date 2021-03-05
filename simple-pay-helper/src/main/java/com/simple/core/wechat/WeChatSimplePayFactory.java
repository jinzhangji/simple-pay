package com.simple.core.wechat;

import com.simple.consts.AppConst;
import com.simple.consts.TerminalConst;
import com.simple.core.*;
import com.simple.enums.WeChatTerminalType;
import com.simple.utils.ObjCacheUtils;

/**
 * 微信支付工厂
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatSimplePayFactory implements SimplePayFactory {

    private PropertiesFactory propertiesFactory;

    public WeChatSimplePayFactory(PropertiesFactory propertiesFactory) {
        this.propertiesFactory = propertiesFactory;
    }

    @Override
    public SimplePay getSimplePay(String terminal) {
        return getSimplePay(terminal,null);
    }


    @Override
    public SimplePay getSimplePay(String terminal, Long propertiesId) {
        WeChatPayProperties properties = getProperties(WeChatTerminalType.getAppType(terminal),propertiesId);
        if(properties == null){
            throw new NullPointerException("No properties");
        }
        String key = new StringBuilder("wechatpay")
                .append("-").append(terminal)
                .append("-").append(properties.getAppId())
                .append("-").append(properties.getMchid())
                .toString();

        return (SimplePay)ObjCacheUtils.get(key,() -> {
            if(terminal.equalsIgnoreCase(TerminalConst.APP)){
                return new WeChatSimplePayApp(properties);
            }else if(terminal.equalsIgnoreCase(TerminalConst.WPP)){
                return new WeChatSimplePayWpp(properties);
            }else if(terminal.equalsIgnoreCase(TerminalConst.H5)){
                return new WeChatSimplePayWap(properties);
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
        WeChatPayProperties properties = getProperties(appType,propertiesId);
        if(properties == null){
            throw new NullPointerException(appType + " not properties");
        }
        String key = new StringBuilder("wehchatauth")
                .append("-").append(appType)
                .append("-").append(properties.getAppId())
                .toString();
        return (SimpleAuth) ObjCacheUtils.get(key,() -> {
            switch (appType) {
                case AppConst.MP:
                    return new WeChatMpSimpleAuth(properties);
                case AppConst.OA:
                    return new WeChatOaSimpleAuth(properties);
                case AppConst.WEBSITE:
                    //pass
                case AppConst.APPLES:
                    //pass
                default:
                    return null;
            }
        });
    }


    private WeChatPayProperties getProperties(String appType,Long propertiesId){
        SimplePayProperties properties;
        if(propertiesId != null){
            properties = propertiesFactory.getProperties(propertiesId);
        }else{
            properties = propertiesFactory.getProperties(appType);
        }
        return (WeChatPayProperties) properties;
    }

}
