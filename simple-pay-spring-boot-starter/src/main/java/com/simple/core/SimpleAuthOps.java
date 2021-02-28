package com.simple.core;

import com.simple.enums.PayMethod;

/**
 * Created by Jin.Z.J  2021/2/23
 */
public interface SimpleAuthOps {

    SimpleAuth auth(String appType);

    SimpleAuth mp();

    SimpleAuth website();

    SimpleAuth oa();

    SimpleAuth apples();

    SimpleAuth getSimpleAuth(PayMethod method, String appType);


}
