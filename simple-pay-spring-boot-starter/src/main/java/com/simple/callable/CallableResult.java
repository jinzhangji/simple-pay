package com.simple.callable;

/**
 * 回调业务结果
 * Created by Jin.Z.J  2020/11/26
 */
public class CallableResult {
    /**
     * 业务结果
     */
    private boolean success;
    /**
     * 其他业务数据
     */
    private Object data;

    public CallableResult(boolean success) {
        this.success = success;
    }

    public CallableResult(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static CallableResult ok(){
        return ok(true,null);
    }

    public static CallableResult ok(boolean success,Object data){
        return new CallableResult(success,data);
    }


}
