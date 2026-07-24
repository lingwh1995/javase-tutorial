package org.bluebridge.java10;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 局部变量类型推断
 *
 * @author lingwh
 * @date 2026/4/21 10:30
 */
public class LocalVariableTypeInterfaceTest {

    /**
     * 局部变量类型推断适用于以下情况
     */
    @Test
    public void testLocalVariableTypeInterface1() {
        // 1.声明变量时，根据所赋的值，推断变量的类型
        var i = 10;
        var s = "hello java";
        var list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("--------------------------------------");

        // 2.遍历操作(增强 for 循环)
        for (var j : list) {
            System.out.println(j);
        }
        System.out.println("--------------------------------------");

        // 3.遍历操作(普通 for 循环)
        for (var k = 0; k < list.size(); k++) {
            System.out.println(k);
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 局部变量类型推断不适用于以下情况
     */
    @Test
    public void testLocalVariableTypeInterface2() {
        // 1.右值为 null
        // var i = null;

        // 2.方法引用
        // var j = System.out::println;

        // 3.lambda 表达式
        // var k = () -> Math.random();

        // 4.为数组静态初始化
        // var arr = {1,2,3};

        // 5.没有初始化的 局部变量声明
        // var h;

        // 6.方法的返回值类型

        // 7.方法的参数类型

        // 8.构造器的参数类型

        // 9.类的属性

        // 10.catch 块
        // try {
        //
        // }catch (var e) {
        // var x = 0;
        // e.printStackTrace();
        // }
    }
}
