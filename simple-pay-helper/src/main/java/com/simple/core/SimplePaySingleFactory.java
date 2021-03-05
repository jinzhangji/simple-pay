package com.simple.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;


/**
 * 生产单例 SimplePay 工厂
 * Created by Jin.Z.J  2020/11/25
 */
public abstract class SimplePaySingleFactory implements SimplePayFactory{

    /**缓存已经生产过的 SimplePay 对象*/
    private static final SimpleCache<Object> payCache = new SimpleCache<>();
    /**缓存已经生产过的 SimpleAuth 对象*/
    private static final SimpleCache<Object> authCache = new SimpleCache<>();

    private static class SimpleCache<T>{

        private final Map<String,T> cache = new ConcurrentHashMap<>();

        public T get(String key, Supplier<T> supplier){
            T obj = cache.get(key);
            if(obj == null){
                obj = supplier.get();
                if(obj != null){
                    this.cache.put(key,obj);
                }
            }
            return obj;
        }
    }



    @Override
    public SimplePay getSimplePay(String terminal) {
        String attrKey = this.getKey(terminal);
        return (SimplePay)payCache.get(attrKey,() -> this.createSimplePay(terminal));
    }




    @Override
    public SimpleAuth getSimpleAuth(String appType) {
        String attrKey = this.getKey(appType);
        return (SimpleAuth)authCache.get(attrKey,() -> this.createSimpleAuth(appType));
    }

    /**
     * 唯一key
     * @param flag 支付传终端,授权传应用类型
     * @return
     */
    protected abstract String getKey(String flag);


    /**
     * 创建
     * @param terminal
     * @return
     */
    protected abstract SimplePay createSimplePay(String terminal);


    /**
     * 创建simpleAuth
     * @param appType
     * @return
     */
    protected abstract SimpleAuth createSimpleAuth(String appType);




}
