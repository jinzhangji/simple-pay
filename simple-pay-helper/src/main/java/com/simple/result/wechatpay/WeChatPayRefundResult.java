package com.simple.result.wechatpay;

import com.simple.result.SimplePayResult;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class WeChatPayRefundResult extends WeChatPayBaseResult implements SimplePayResult {

    private static final String SUCCESSFUL = "success";

    private String 	return_code;//返回状态码	return_code	是	String(16)	SUCCESS	SUCCESS/FAIL此字段是通信标识，表示接口层的请求结果，并非退款状态。
    private String 	return_msg;//返回信息	return_msg	是	String(128)	OK	当return_code为FAIL时返回信息为错误原因 ，例如签名失败参数格式校验错误以下字段在return_code为SUCCESS的时候有返回字段名	变量名	必填	类型	示例值	描述
    private String 	result_code;//业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL/SUCCESS退款申请接收成功，结果通过退款查询接口查询FAIL 提交业务失败
    private String 	err_code;//错误代码	err_code	否	String(32)	SYSTEMERROR	列表详见错误码列表
    private String 	err_code_des;//错误代码描述	err_code_des	否	String(128)	系统超时	结果信息描述
    private String 	appid;//公众账号ID	appid	是	String(32)	wx8888888888888888	微信分配的公众账号ID
    private String 	mch_id;//商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号
    private String 	nonce_str;//随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位
    private String 	sign;//签名	sign	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	签名，详见签名算法
    private String 	transaction_id;//微信订单号	transaction_id	是	String(32)	4007752501201407033233368018	微信订单号
    private String 	out_trade_no;//商户订单号	out_trade_no	是	String(32)	33368018	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String 	out_refund_no;//商户退款单号	out_refund_no	是	String(64)	121775250	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
    private String 	refund_id;//微信退款单号	refund_id	是	String(32)	2007752501201407033233368018	微信退款单号
    private Integer 	refund_fee;//退款金额	refund_fee	是	Integer	100	退款总金额,单位为分,可以做部分退款
    private Integer 	settlement_refund_fee;//应结退款金额	settlement_refund_fee	否	Integer	100	去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
    private Integer 	total_fee;//标价金额	total_fee	是	Integer	100	订单总金额，单位为分，只能为整数，详见支付金额
    private Integer 	settlement_total_fee;//应结订单金额	settlement_total_fee	否	Integer	100	去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
    private String 	fee_type;//标价币种	fee_type	否	String(8)	CNY	订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private Integer 	cash_fee;//现金支付金额	cash_fee	是	Integer	100	现金支付金额，单位为分，只能为整数，详见支付金额
    private String 	cash_fee_type;//现金支付币种	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private Integer 	cash_refund_fee;//现金退款金额	cash_refund_fee	否	Integer	100	现金退款金额，单位为分，只能为整数，详见支付金额
    private Integer 	coupon_refund_fee;//代金券退款总金额	coupon_refund_fee	否	Integer	100	代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
    private Integer 	coupon_refund_count;//退款代金券使用数量	coupon_refund_count	否	Integer	1	退款代金券使用数量

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

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public Integer getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public void setSettlement_refund_fee(Integer settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
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

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(Integer cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    public Integer getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(Integer coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public Integer getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(Integer coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }
    @Override
    public boolean isSuccess(){
        return SUCCESSFUL.equalsIgnoreCase(this.return_code) && SUCCESSFUL.equalsIgnoreCase(this.result_code);
    }

}
