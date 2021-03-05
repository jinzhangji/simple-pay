package com.simple.callable;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现业务类注册登记到manager中进行代理
 * Created by Jin.Z.J  2020/11/26
 */
public abstract class AbstractSimplePayCallable implements SimplePayCallable, InitializingBean {

    @Autowired
    private SimplePayCallableManager callbackManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        callbackManager.register(busCode(),this);
    }

    /**
     * 业务代码 ：业务代码必须以订单号 startWith 匹配
     * @return
     */
    public abstract String busCode();


}
