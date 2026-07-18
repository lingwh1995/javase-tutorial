package org.bluebridge.constructor.no_args_constructor;

import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User实体，使用@NoArgsConstructor为类添加无参构造方法
 *
 * @author lingwh
 * @date 2025/8/18 11:32
 */
@NoArgsConstructor
@ToString
public class User {

    private String id;
    private String name;
    private int age;
    private String email;
}
