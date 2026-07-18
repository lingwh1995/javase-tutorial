package org.bluebridge.constructor.all_args_constructor;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * User实体，使用@AllArgsConstructor添加包含所有属性的有参构造方法
 *
 * @author lingwh
 * @date 2025/8/18 11:28
 */
@AllArgsConstructor
@ToString
public class User {

    private String id;
    private String name;
    private int age;
    private String email;
}
