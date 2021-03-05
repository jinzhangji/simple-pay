package com.simple.result.wechatpay;

import com.simple.result.SimplePayResult;

/**
 * Created by Jin.Z.J  2021/2/7
 */
public class WeChatPayQueryRefundResult extends WeChatPayBaseResult implements SimplePayResult {

    private String return_code;
    private String return_msg;

    private String     result_code; //业务结果    result_code 是   String(16)  SUCCESS  SUCCESS/FAIL SUCCESS退款申请接收成功，退款结果以退款状态为准 FAIL
    private String  err_code; //错误码 err_code    是   String(32)  SYSTEMERROR 错误码详见第6节
    private String     err_code_des; //错误描述    err_code_des    是   String(128) 系统错误    结果信息描述
    private String   appid; //公众账号ID  appid   是   String(32)  wx8888888888888888  微信分配的公众账号ID（企业号corpid即为此appId）
    private String  mch_id; //商户号 mch_id  是   String(32)  1900000109  微信支付分配的商户号
    private String    nonce_str; //随机字符串   nonce_str   是   String(32)  5K8264ILTKCH16CQ2502SI8ZNMTM67VS    随机字符串，不长于32位
    private String   sign; //签名  sign    是   String(32)  C380BEC2BFD727A4B6845133519F3AD6    签名，详见签名算法
    private Integer  total_refund_count; //订单总退款次数 total_refund_count  否   Int 35  订单总共已发生的部分退款次数，当请求参数传入offset后有返回
    private String    transaction_id; //微信订单号   transaction_id  是   String(32)  1217752501201407033233368018    微信订单号
    private String    out_trade_no; //商户订单号   out_trade_no    是   String(32)  1217752501201407033233368018    商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private Integer     total_fee; //订单金额    total_fee   是   Int 100 订单总金额，单位为分，只能为整数，详见支付金额
    private Integer   settlement_total_fee; //应结订单金额  settlement_total_fee    否   Int 100 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
    private String     fee_type; //货币种类    fee_type    否   String(8)   CNY 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private Integer   cash_fee; //现金支付金额  cash_fee    是   Int 100 现金支付金额，单位为分，只能为整数，详见支付金额
    private Integer     refund_count; //退款笔数    refund_count    是   Int 1   当前返回退款笔数


    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
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

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getTotal_refund_count() {
        return total_refund_count;
    }

    public void setTotal_refund_count(Integer total_refund_count) {
        this.total_refund_count = total_refund_count;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public Integer getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(Integer settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public Integer getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(Integer refund_count) {
        this.refund_count = refund_count;
    }

    @Override
    public boolean isSuccess() {
        return SUCCESSFUL.equalsIgnoreCase(this.return_code) && SUCCESSFUL.equalsIgnoreCase(this.result_code);
    }
}
