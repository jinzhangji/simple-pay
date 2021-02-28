package com.simple.core.wechat;

import com.simple.consts.AppConst;
import com.simple.consts.TerminalConst;
import com.simple.core.PropertiesFactory;
import com.simple.core.SimpleAuth;
import com.simple.core.SimplePay;
import com.simple.core.SimplePayFactory;
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
        String appType = WeChatTerminalType.getAppType(terminal);
        if(appType == null){
            throw new NullPointerException("terminal not app");
        }
        WeChatPayProperties properties = (WeChatPayProperties)propertiesFactory.getProperties(appType);
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
        WeChatPayProperties properties = (WeChatPayProperties)propertiesFactory.getProperties(appType);
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


}
