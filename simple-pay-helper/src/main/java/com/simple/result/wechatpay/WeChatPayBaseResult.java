package com.simple.result.wechatpay;

import com.simple.annotation.Exclude;

import java.util.Map;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatPayBaseResult {

    public static final String SUCCESSFUL = "SUCCESS";

    @Exclude
    private Long properties_id;

    private Map<String,Object> moreRes;
    private String apiRes;

    public Map<String, Object> getMoreRes() {
        return moreRes;
    }

    public void setMoreRes(Map<String, Object> moreRes) {
        this.moreRes = moreRes;
    }

    public String getApiRes() {
        return apiRes;
    }

    public void setApiRes(String apiRes) {
        this.apiRes = apiRes;
    }

    public Long getProperties_id() {
        return properties_id;
    }

    public void setProperties_id(Long properties_id) {
        this.properties_id = properties_id;
    }
}
