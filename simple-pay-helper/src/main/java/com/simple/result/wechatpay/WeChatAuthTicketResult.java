package com.simple.result.wechatpay;

import com.simple.result.SimplePayResult;

/**
 * Created by Jin.Z.J  2021/2/25
 */
public class WeChatAuthTicketResult implements SimplePayResult {

    private Integer errcode;
    private String errmsg;

    private String ticket;
    private Integer  expires_in;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public boolean isSuccess() {
        return this.errcode == 0;
    }

}
