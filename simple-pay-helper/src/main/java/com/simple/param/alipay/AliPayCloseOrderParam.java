package com.simple.param.alipay;

import com.alipay.api.response.AlipayTradeCloseResponse;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class AliPayCloseOrderParam extends AliAbstractSimplePayParam<AlipayTradeCloseResponse> {

    private String trade_no;//	String	特殊可选	64	该交易在支付宝系统中的交易流水号。最短 16 位，最长 64 位。和out_trade_no不能同时为空，如果同时传了 out_trade_no和 trade_no，则以 trade_no为准。	2013112611001004680073956707
    private String out_trade_no;//	String	特殊可选	64	订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no	HZ0120131127001
    private String operator_id;//	String	可选	28	卖家端自定义的的操作员 ID

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

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    @Override
    public String requestURI() {
        return null;
    }

    @Override
    public Class<AlipayTradeCloseResponse> resClass() {
        return AlipayTradeCloseResponse.class;
    }
}
