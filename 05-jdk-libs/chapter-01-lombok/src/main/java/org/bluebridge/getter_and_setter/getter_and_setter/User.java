package org.bluebridge.getter_and_setter.getter_and_setter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lingwh
 * @desc User实体，在类上使用@Getter注解和@Setter注解，被final或者static修饰的属性，lombok无法为其生成指定的Getter()方法和Setter()方法
 * @date 2025/8/18 11:56
 */
@Getter
@Setter
public class User {
    private String id;
    private String name;
    private static int age;

    // 设置不为email这个属性生成的Getter()方法和Setter()方法
    @Getter(AccessLevel.NONE)
    private String email;
}
