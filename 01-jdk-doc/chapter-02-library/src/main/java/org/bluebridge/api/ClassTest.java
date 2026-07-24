package org.bluebridge.api;

import org.junit.Test;

/**
 * Class 类 API 测试
 *
 * @author lingwh
 * @date 2019/3/28 16:29
 */
public class ClassTest {

    /**
     * 根据 Class 信息判断一个对象是不是数组
     */
    @Test
    public void isArrayTest() {
        int[] arr = { 1, 2, 3, 4, 5 };
        String str = "zhagnsan";
        System.out.println(arr.getClass().isArray());
        System.out.println(str.getClass().isArray());
    }

    /**
     * 根据 Class 信息获取该 Class 对象的 SuperClass 信息
     */
    @Test
    public void getSuperClassTest() {
        int[] nums = { 1, 2, 3, 4, 5 };
        String str = "zhagnsan";
        Class<? extends int[]> numsClass = nums.getClass();
        Class<?> numsSuperclass = numsClass.getSuperclass();
        System.out.println("nums对象的SuperClass:" + numsSuperclass);

        Class<? extends String> stringClass = str.getClass();
        Class<?> stringSuperclass = stringClass.getSuperclass();
        System.out.println("str对象的SuperClass:" + stringSuperclass);
    }

    /**
     * 获取返回表示数组组件类型的 Class
     */
    @Test
    public void getComponentTypeTest() {
        Integer[] nums = new Integer[4];
        // 获取返回表示数组组件类型的 Class
        System.out.println("getComponentType():" + nums.getClass().getComponentType());
        System.out.println("getName():" + nums.getClass().getName());

        String string = "我是你爸爸";
        System.out.println("getComponentType():" + string.getClass().getComponentType());
        System.out.println("getName():" + string.getClass().getName());
    }

    /**
     * 判断对象是否枚举
     */
    @Test
    public void isEnumTest() {
        Integer[] nums = new Integer[4];
        System.out.println("判断对象是不是枚举:" + nums.getClass().isEnum());
    }

    @Test
    public void getNameAndGetSimpleName() {
        String str = "hello world~";
        System.out.println("str.getClass().getSimpleName() = " + str.getClass().getSimpleName());
        System.out.println("str.getClass().getName() = " + str.getClass().getName());
    }
}
