package com.simple.core;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public interface SimpleAuthOps {

    SimpleAuth auth(String appType);

    SimpleAuth mp();

    SimpleAuth website();

    SimpleAuth oa();

    SimpleAuth apples();

}
