package com.simple.enums;

import com.simple.consts.AppConst;
import com.simple.consts.TerminalConst;

/**
 * Created by Jin.Z.J  2021/2/24
 */
public enum WeChatTerminalType {

    H5(TerminalConst.H5, AppConst.OA),

    PC(TerminalConst.PC,AppConst.WEBSITE),

    APP(TerminalConst.APP,AppConst.MP),

    WPP(TerminalConst.WPP,AppConst.OA),

    APPLETS(TerminalConst.APPLETS,AppConst.APPLES)

    ;

    private String terminal;
    private String appType;

    WeChatTerminalType(String terminal, String appType) {
        this.terminal = terminal;
        this.appType = appType;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }


    public static String getAppType(String terminal){
        for (WeChatTerminalType terminalRef : WeChatTerminalType.values()) {
            if(terminalRef.getTerminal().equalsIgnoreCase(terminal)){
                return terminalRef.getAppType();
            }
        }
        return null;
    }

}
