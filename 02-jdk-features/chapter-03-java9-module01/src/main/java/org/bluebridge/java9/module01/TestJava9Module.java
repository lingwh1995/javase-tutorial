package org.bluebridge.java9.module01;

import org.bluebridge.java9.module02.Person;

/**
 * Java9 模块化测试
 *
 * @author lingwh
 * @date 2025/1/24 15:42
 */
public class TestJava9Module {

    public static void main(String[] args) {
        Person person = new Person("张三", 28);
        System.out.println(person);
    }
}
