# simple-pay
### 项目介绍
   simple-pay(简单支付)集成了支付宝和微信支付(部分),在实际开发中没有可用整合sdk。因支付项目多,提取部分代码整合,无缝集成了SpringBoot。使用方便。
##### 联系作者：765426233@qq.com
### 实用功能 
+ 微信实现终端：APP、H5、JSAPI 支持：下单、查询(交易查询,退款查询)、关闭、退款,授权登陆相关..
+ 支付宝实现终端:APP、H5 支持：下单、查询(交易查询,退款查询)、关闭、退款

### 建议开发者使用以下环境，可以避免版本带来的问题
    JDK: JDK1.8+
    Maven: 3.6.1
    SpringBoot版本: 2.+

### 微信支付
+ 配置文件
    
    - 应用类型:mp(移动应用)、oa(公众号)、apples(小程序/暂不支持),websie(网页应用/暂不支持)
    - 支持多商户配置(随机获取)
    - 需要h5或公众号支付/授权 需要配置oa
    - 需要app支付/授权 需要配置应用mp
    - 其他暂未实现...
    
    
    
    simple-pay:
      wechat:
        mp:
            #唯一id，所有应用id必须唯一
          - id: 1  
            app-id: example  
            secret: example
            #商户号
            mchid: example 
            #签名秘钥串
            sign-key: example 
            #退款证书,classpath:取源文件路径
            pk12-path: classpath:example.p12 
            refund-notify-url: https://example/api/notify/refund/{id}
            notify-url: https://example/api/notify/pay/{id}
          - id: 2  
            app-id: example 
            secret: example
            mchid: example 
            sign-key: example
            pk12-path: classpath:example.p12
            refund-notify-url: https://example/api/notify/refund/{id}
            notify-url: https://example/api/notify/pay/{id}
        oa:
          - id: 3
            app-id: example
            secret: example
            mchid: example
            sign-key: example
            pk12-path: classpath:example.p12
            refund-notify-url: https://example/api/notify/refund/{id}
            notify-url: https://example/api/notify/pay/{id}


+ 启用数据库配置


        
        #项目导入 wechat_pay_config.sql 自己管理  
        simple-pay:
          wechat-db: true
      
+ 使用样例 SpringBoot :
    - 微信创建订单
    
  
(```)
    @Autowired
    private SimplePayTemplate simplePayTemplate;
    
    @Test
    public void testUnifiedOrder(){
    
        WeChatPayUnifiedOrderParam unifiedOrderParam = SimplePays.WeChat.createUnifiedOrderParam();
        //32位随机字符串
        unifiedOrderParam.setNonce_str(“123123”);
        unifiedOrderParam.setBody("付款");
        //金额/分
        unifiedOrderParam.setTotal_fee(1);
        //用户ip
        unifiedOrderParam.setSpbill_create_ip("127.0.0.1");
        //订单主键
        unifiedOrderParam.setOrder_id(1L);
        //订单号
        unifiedOrderParam.setOut_trade_no(“orderNo”);
        //公众号支付必传
        unifiedOrderParam.setOpenid(“openId”);
        //失效时间
        unifiedOrderParam.setTime_expire("日期格式yyyyMMddHHmmss");
        WeChatUnifiedOrderResult result;
        try {
            result = simplePayTemplate.pay().app().unifiedOrder(unifiedOrderParam);
        } catch (SimplePayException e) {
            // 异常
        }
        if (!result.isSuccess()) {
            //失败
        }
        System.out.println(JSON.toJSONString(result))
    }
(```)

#### 支付宝暂未实现,数据库配置,授权...