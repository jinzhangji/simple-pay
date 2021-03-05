package com.simple.exception;

/**
 * Created by Jin.Z.J  2020/11/25
 */
public class SimplePayException extends Exception{

    private Integer status = 500;
    private String message;
    private Object data;

    public SimplePayException(Exception e) {
        super(e);
        this.message = e.getMessage();
    }

    public SimplePayException(String message) {
        super(message);
        this.message = message;
    }

    public SimplePayException(Integer status,String message){
        this(message);
        this.status = status;
    }

    public SimplePayException(String messageTpl, String ... args) {
        this(String.format(messageTpl,args));
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
