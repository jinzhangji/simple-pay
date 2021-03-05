package com.simple.param.alipay;

import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class AliPayQueryRefundParam extends AliAbstractSimplePayParam<AlipayTradeFastpayRefundQueryResponse> {

    private String trade_no;//	String	特殊可选	64	支付宝交易号，和商户订单号不能同时为空	20150320010101001
    private String out_trade_no;//	String	特殊可选	64	订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no	2014112611001004680073956707
    private String out_request_no;//	String	必选	64	请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号	2014112611001004680073956707
    private String org_pid;//	String	可选	16	银行间联模式下有用，其它场景请不要使用；双联通过该参数指定需要查询的交易所属收单机构的pid;	2088101117952222
    private String []query_options;///	String[]	可选	1024	查询选项，商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式。如：["refund_detail_item_list"]	refund_detail_item_list

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getOrg_pid() {
        return org_pid;
    }

    public void setOrg_pid(String org_pid) {
        this.org_pid = org_pid;
    }

    public String[] getQuery_options() {
        return query_options;
    }

    public void setQuery_options(String[] query_options) {
        this.query_options = query_options;
    }

    @Override
    public String requestURI() {
        return null;
    }

    @Override
    public Class<AlipayTradeFastpayRefundQueryResponse> resClass() {
        return AlipayTradeFastpayRefundQueryResponse.class;
    }
}
