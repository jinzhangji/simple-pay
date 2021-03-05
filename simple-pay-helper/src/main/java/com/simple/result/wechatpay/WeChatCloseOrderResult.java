package com.simple.result.wechatpay;

import com.simple.result.SimplePayResult;

/**
 * Created by Jin.Z.J  2021/2/5
 */
public class WeChatCloseOrderResult extends WeChatPayBaseResult implements SimplePayResult {

    private String nonce_str;
    private String appid;
    private String sign;
    private String return_msg;
    private String result_code;
    private String mch_id;
    private String sub_mch_id;
    private String return_code;

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    @Override
    public boolean isSuccess() {
        return SUCCESSFUL.equalsIgnoreCase(this.return_code) && SUCCESSFUL.equalsIgnoreCase(this.result_code);
    }
}
