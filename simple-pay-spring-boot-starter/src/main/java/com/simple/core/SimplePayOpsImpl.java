package com.simple.core;

import com.simple.consts.TerminalConst;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public class SimplePayOpsImpl implements SimplePayOps {

    private SimplePayMethodFactory factory;

    public SimplePayOpsImpl(SimplePayMethodFactory factory) {
        this.factory = factory;
    }

    @Override
    public SimplePay terminal(String terminal) {
        return new SimplePayProxy(terminal,factory);
    }

    @Override
    public SimplePay h5() {
        return this.terminal(TerminalConst.H5);
    }

    @Override
    public SimplePay app() {
        return this.terminal(TerminalConst.APP);
    }

    @Override
    public SimplePay pc() {
        return this.terminal(TerminalConst.PC);
    }

    @Override
    public SimplePay wpp() {
        return this.terminal(TerminalConst.WPP);
    }

    @Override
    public SimplePay applets() {
        return this.terminal(TerminalConst.APPLETS);
    }

}
