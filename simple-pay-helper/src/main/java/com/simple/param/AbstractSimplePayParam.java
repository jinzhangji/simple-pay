package com.simple.param;

import com.simple.annotation.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public abstract class AbstractSimplePayParam<R> implements SimplePayParam<R> {


    private Map<String,Object> otherParam;
    @Exclude
    private Long propertiesId;

    public Map<String, Object> getOtherParam() {
        return otherParam;
    }

    @Override
    public SimplePayParam<R> addParam(String key, Object value) {
        if(this.otherParam == null){
            this.otherParam = new HashMap<>();
        }
        this.otherParam.put(key,value);
        return this;
    }

    @Override
    public Long propertiesId() {
        return this.getPropertiesId();
    }

    public Long getPropertiesId() {
        return propertiesId;
    }

    public void setPropertiesId(Long propertiesId) {
        this.propertiesId = propertiesId;
    }
}
