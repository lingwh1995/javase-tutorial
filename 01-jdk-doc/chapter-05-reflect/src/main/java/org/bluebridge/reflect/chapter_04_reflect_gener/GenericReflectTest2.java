package org.bluebridge.reflect.chapter_04_reflect_gener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 使用反射获取泛型信息
 * @date 2019/3/25 00:00
 */
public class GenericReflectTest2 {

    public void test01(Map<String, Person> map, List<Person> person) {
        System.out.println("test01()......");
    }

    public Map<Integer, Person> test02() {
        System.out.println("test02()......");
        return null;
    }

    /**
     * 获取指定方法泛型参数信息
     */
    @Test
    public void fun1() throws NoSuchMethodException, SecurityException {
        Class<GenericReflectTest2> gf2 = GenericReflectTest2.class;
        Method method = gf2.getMethod("test01", Map.class, List.class);
        Type[] parameterTypes = method.getGenericParameterTypes();
        for (Type parameterType : parameterTypes) {
            System.out.println("#:" + parameterType);
            if (parameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) parameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("*:" + actualTypeArgument);
                }
            }
        }
    }

    /**
     * 获取指定方法返回值泛型信息
     */
    @Test
    public void fun2() throws NoSuchMethodException, SecurityException {
        Class<GenericReflectTest2> gf2 = GenericReflectTest2.class;
        Method method = gf2.getMethod("test02", null);
        Type returnType = method.getGenericReturnType();
        System.out.println("#:" + returnType);
        if (returnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println("*:" + actualTypeArgument);
            }
        }
    }

    /**
     * 反射突破泛型限制
     */
    @Test
    public void fun3()
            throws NoSuchMethodException,
                    SecurityException,
                    IllegalAccessException,
                    IllegalArgumentException,
                    InvocationTargetException {
        ArrayList<String> list = new ArrayList<String>();
        list.add("100");
        list.add("200");
        list.add("300");
        // 直接调用下面的方法，会报错
        // list.add(100);
        System.out.println("使用反射调用设置值之前:" + list);
        /**
         * 使用反射调用，不会报错
         */
        Class<? extends ArrayList> clazz = list.getClass();
        Method method = clazz.getDeclaredMethod("add", Object.class);
        method.invoke(list, 400);
        System.out.println("使用反射设置值之后:" + list);
    }
}
