package com.simple.utils;

import com.simple.annotation.Exclude;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * bean 工具类
 * Created by Jin.Z.J  2020/11/25
 */
public class BeanUtils {

    public static final Object[] GET_INVOKE_PARAMS = new Object[0];
    private static final String EXCLUDE_PROPERTY_NAME = "class";

    private static final String SUPER_CLASS_NAME = "java.lang.Object";


    private static final Map<Class,List<BeanProp>> cache = new ConcurrentHashMap<>();

    public static class BeanProp{

        private Field field;
        private Method getter;
        private Method setter;

        public BeanProp(Field field, Method getter, Method setter) {
            this.field = field;
            this.getter = getter;
            this.setter = setter;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public String getName() {
            return field.getName();
        }

        public Method getter() {
            return getter;
        }

        public Method setter() {
            return setter;
        }
    }



    /**
     * 获取属性描述器
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T>  PropertyDescriptor[] getPropertyDescriptors(Class<T> clazz) throws Exception{
        BeanInfo benInfo = Introspector.getBeanInfo(clazz);
        return benInfo.getPropertyDescriptors();
    }




    private static <T> List<BeanProp> getBeanProp(Class<T> clazz){
        List<BeanProp> beanProps = cache.get(clazz);
        if(beanProps == null){
            try{
                PropertyDescriptor[] propertyDescriptors =  getPropertyDescriptors(clazz);
                beanProps = new ArrayList<>();
                Map<String, Field> mapField = new HashMap<>();

                for(Class<?> c = clazz; c != null; c = c.getSuperclass()){
                    for (Field declaredField : c.getDeclaredFields()) {
                        mapField.put(declaredField.getName(),declaredField);
                    }
                }
                for (PropertyDescriptor propertyDes : propertyDescriptors) {
                    String name = propertyDes.getName();
                    if(EXCLUDE_PROPERTY_NAME.equals(name)){
                        continue;
                    }
                    Field field = mapField.get(name);
                    if(field != null){
                        beanProps.add(new BeanProp(field,propertyDes.getReadMethod(),propertyDes.getWriteMethod()));
                    }
                }
                cache.put(clazz,beanProps);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return beanProps;
    }




    /**
     * 将的JavaBean 转换成  Map<属性名,属性值>
     * @param bean
     * @throws Exception
     */
    public static <T> Map<String,Object> beanToMap(T bean){
        return beanToMap(bean,false);
    }


    /**
     * bean 转 map
     * @param bean
     * @param skipNullVal
     * @param <T>
     * @return
     */
    public static <T> Map<String,Object> beanToMap(T bean,boolean skipNullVal){
        Map<String,Object> map = new HashMap<>();
        foreachVal(bean,(k,v) -> {
            if(skipNullVal && v == null){
                return;
            }
            map.put(k,v);
        });
        return map;
    }





    /**
     * copy Map 属性到 bean 中的字段
     * @param map
     * @param bean
     * @param <T>
     */
    public static <T> void mapToBean(Map<String,?> map,T bean){
        List<BeanProp> beanProps = getBeanProp(bean.getClass());
        try{
            for (BeanProp beanProp : beanProps) {
                String name = beanProp.getName();
                Object object = map.get(name);
                if(object != null){
                    Method method = beanProp.setter();
                    if(method.getParameterTypes().length == 1){//如果没有参数
                        method.invoke(bean,object);
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static <T> T mapToBean(Map<String,Object> map,Class<T> clazz){
        try{
            T obj = clazz.newInstance();
            mapToBean(map,obj);
            return obj;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    public static <T> void foreach(Class<T> clazz, Consumer<BeanProp> biConsumer){
        Objects.requireNonNull(clazz);
        List<BeanProp> beanProps = getBeanProp(clazz);
        for (BeanProp prop : beanProps) {
            biConsumer.accept(prop);
        }
    }




    public static <T> void foreachVal(T obj, BiConsumer<String,Object> biConsumer){
        foreach(obj.getClass(),(prop) -> {
            Field field = prop.getField();
            Exclude exclude = field.getAnnotation(Exclude.class);
            if(exclude != null){
                return;
            }
            String name = field.getName();
            Method method = prop.getter();
            if(method.getParameterTypes().length == 0){//如果没有参数
                Object res = null;
                try{
                    res = method.invoke(obj,GET_INVOKE_PARAMS);
                }catch (Exception e){
                }
                biConsumer.accept(name,res);
            }
        });
    }





    /**
     * 执行实例中的方法
     * @param obj 实例对象
     * @param methodName 要执行的方法
     * @param params 参数列表
     * @param <T>
     * @return
     */
    public static <T> Object invokeMethod(T obj,String methodName,Object ... params){
        try{
            Class clazz = obj.getClass();
            Method method = clazz.getDeclaredMethod(methodName);
            if(method == null){
                return null;
            }
            return method.invoke(obj,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    /**
     * 将标准的JavaBean对象属性值复制到指定的Bean对象中并创建实例,属性名称和类型需要一致
     * @param source 源数据对象
     * @param toClazz 要复制并创建实例的对象
     */
    public static <T,S> T copyProperty(S source,Class<T> toClazz){
        try{
            return copyProperty(source,toClazz.newInstance());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }




    /**
     * 将标准的JavaBean对象属性值复制到指定的Bean对象实例中,存在覆盖
     * @param source 源数据对象
     * @param target 被复制实例的对象
     */
    public static <T,S> T copyProperty(S source,T target){
        List<BeanProp> tBeanProps = getBeanProp(target.getClass());
        Map<String,Method> tBeanPropMap = new HashMap<>();
        for (BeanProp tBeanProp : tBeanProps) {
            tBeanPropMap.put(tBeanProp.getName(),tBeanProp.setter());
        }
        foreachVal(source,(name,value) -> {
            Method method = tBeanPropMap.get(name);
            if(method != null && value != null){
                Class<?>[] clazzArr = method.getParameterTypes();
                if(clazzArr != null && clazzArr.length == 1 && clazzArr[0] == value.getClass()){
                    try{
                        method.invoke(target,value);
                    }catch (Exception e){
                    }
                }
            }
        });
        return target;
    }


}
