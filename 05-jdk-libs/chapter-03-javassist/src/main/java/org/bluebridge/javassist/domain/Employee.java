package org.bluebridge.javassist.domain;

import lombok.Data;

/**
 * 测试hsqldb基本功能
 *
 * @author lingwh
 * @date 2025/8/18 14:30
 */
@Data
public class Employee {
    private String id;
    private int age;
    private String name;

    public Employee() {
        super();
    }

    public String sayHello(String param) {
        System.out.println("我是参数:" + param);
        return "I am ok!";
    }
}
