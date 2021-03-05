package com.simple.core;

import com.simple.enums.PayMethod;

/**
 * Created by Jin.Z.J  2021/3/1
 */
public abstract class AbstractPropertiesFactory {

    public abstract PropertiesFactory getFactory(PayMethod method);

}
