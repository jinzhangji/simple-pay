package com.simple.core;

/**
 * Created by Jin.Z.J  2021/3/5
 */
public interface ErrorHandler {

    void error(Throwable t);

    void failure(String code,String message);

}
