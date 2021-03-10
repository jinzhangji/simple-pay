
package com.simple.enums;

import com.simple.consts.AppConst;
import com.simple.consts.TerminalConst;

public enum AliTerminalType {


    H5(TerminalConst.H5, AppConst.MP),

    APP(TerminalConst.APP,AppConst.MP),

    WPP(TerminalConst.WPP,AppConst.OA),

    PC(TerminalConst.PC,AppConst.MP),

    APPLETS(TerminalConst.APPLETS,AppConst.APPLES),

   ;

    AliTerminalType(String terminal, String type) {
        this.terminal = terminal;
        this.type = type;
    }

    private String terminal;
    private String type;

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getAppType(String terminal){
        for (AliTerminalType terminalRef : AliTerminalType.values()) {
            if(terminalRef.getTerminal().equalsIgnoreCase(terminal)){
                return terminalRef.getType();
            }
        }
        return null;
    }

}