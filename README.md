# simple-pay
### 项目介绍
   simple-pay(简单支付)集成了支付宝和微信支付(部分),在实际开发中没有可用整合sdk。因支付项目多,提取部分代码整合,无缝集成了SpringBoot。使用方便。
##### 联系作者：765426233@qq.com
### 实用功能 
+ 微信实现终端：APP、H5、JSAPI 支持：下单、查询(交易查询,退款查询)、关闭、退款
+ 支付宝实现终端:APP、H5 支持：下单、查询(交易查询,退款查询)、关闭、退款

### 建议开发者使用以下环境，可以避免版本带来的问题
    JDK: JDK1.8+
    Maven: 3.6.1
    SpringBoot版本: 2.+

### 配置文件

    simple-pay:
      alipay:
        app-id: appid
        private-key: 私钥
        ali-pay-public-key: 支付宝公钥
        notify-url: 统一支付回调地址
      wechat-pay:
        sign-key: 签名加密key
        wpp-app-id: 公众平台 appid
        mchid: 商户号
        woa-app-id: 开放平台 appid
        pk12-path: 退款p12证书，默认取classpath路径,例如 refund.p12(有效性待验证)
        notify-url: 统一支付回调地址

### 支付模板:SimplePayTemplate 
        
        /**
         * 指定终端支付
         * @param terminal
         * @return
         */
        SimplePay terminal(String terminal);
    
        /**
         * h5支付
         * @return
         */
        SimplePay h5();
    
        /**
         * app支付
         * @return
         */
        SimplePay app();
    
        /**
         *
         * 获取PC
         * @return
         */
        SimplePay pc();
    
        /**
         * 微信公众号支付
         * @return
         */
        SimplePay wpp();
    
        /**
         * 根据支付方式和终端获取
         * @param method   支付方式
         * @param terminal 终端
         * @return
         */
        SimplePay getSimplePay(PayMethod method, String terminal);
    
        /**
         * 获取微信指定终端
         * @param terminal
         * @return
         */
        SimplePay getWechatPay(String terminal);
    
    
        /**
         * 获取支付宝指定终端
         * @param terminal
         * @return
         */
        SimplePay getAliPay(String terminal);

### 支持终端
    
    public class TerminalConst {
    
        //h5移动端
        public static final String H5 = "h5";
        //pc网页
        public static final String PC = "pc";
        //app
        public static final String APP = "app";
        //微信公众号
        public static final String WPP = "wpp";
    
    
    }


### 创建业务参数对象
    //微信统一下单参数对象
    SimplePays.WeChat.createUnifiedOrderParam()
    //支付宝统一下单参数对象
    SimplePays.Ali.createUnifiedOrderParam()
    //其他业务参数
    ...   
    

### 使用样例

    @Autowired
    private SimplePayTemplate simplePayTemplate;
    
    WechatPayUnifiedOrderParam unifiedOrderParam = SimplePays.WeChat.createUnifiedOrderParam();
    unifiedOrderParam.setNonce_str(IdUtil.simpleUUID());
    unifiedOrderParam.setBody("测试支付");
    unifiedOrderParam.setTotal_fee(10);
    unifiedOrderParam.setSpbill_create_ip(userIp);
    unifiedOrderParam.setOrder_id(orderId);
    unifiedOrderParam.setOpenid(openId);//woa 支付使用。
    unifiedOrderParam.setOut_trade_no(orderNo);
    unifiedOrderParam.setRedirect_url(redirectUrl);//h5重定向链接
    try{
       WechatUnifiedOrderResult unifiedOrderResult = simplePayTemplate.h5().unifiedOrder(unifiedOrderParam);
       if(!unifiedOrderResult.isSuccess()){
           //失败
       }else{
            //成功
       }
    }catch (SimplePayException e){
       //异常
    }    

    //其他业务API 
    ...


    
    
