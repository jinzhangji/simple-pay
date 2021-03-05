package com.simple.enums;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public enum PayMethod {


    /** 微信支付*/
    WECHAT(0,"WECHATPAY","微信支付"),
    /** 支付宝支付*/
    ALI(1,"ALIPAY","支付宝支付")

    ;

    PayMethod(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    private Integer type;
    private String name;
    private String desc;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static PayMethod valueOf(Integer type){
        for (PayMethod value : PayMethod.values()) {
            if(value.type.equals(type)){
                return value;
            }
        }
        return null;
    }


}
