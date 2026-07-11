package org.bluebridge.java9.module01;

import org.bluebridge.java9.module02.Person;

/**
 * @author lingwh
 * @desc Java9模块化测试
 * @date 2026/7/9 00:00
 */
public class TestJava9Module {

    public static void main(String[] args) {
        Person person = new Person("张三", 28);
        System.out.println(person);
    }
}
