package org.bluebridge.tostring;

import lombok.ToString;

/**
 * @author lingwh
 * @desc User实体，在属性上使用@ToString注解，被static关键字修饰的属性不支持@ToString注解
 * @date 2025/8/18 13:47
 */
// @ToString
// 设置哪些字段在toString()方法中不会被输出
@ToString(exclude = {"email"})
public class User {
    private String id;
    private String name;
    private int age;
    private String email;
}
