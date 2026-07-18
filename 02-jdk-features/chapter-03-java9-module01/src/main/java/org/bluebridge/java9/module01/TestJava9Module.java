package org.bluebridge.java9.module01;

import org.bluebridge.java9.module02.Person;

/**
 * Java9模块化测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TestJava9Module {

    public static void main(String[] args) {
        Person person = new Person("张三", 28);
        System.out.println(person);
    }
}
