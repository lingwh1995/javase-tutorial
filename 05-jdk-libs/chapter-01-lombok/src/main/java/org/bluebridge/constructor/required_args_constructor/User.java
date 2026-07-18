package org.bluebridge.constructor.required_args_constructor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * User实体，使用@RequiredArgsConstructor添加有参构造方法，只针对被final修饰或者@NonNull修饰的属性生成构造函数
 *
 * @author lingwh
 * @date 2025/8/18 11:36
 */
@RequiredArgsConstructor
@ToString
public class User {

    private final String id;
    @NonNull private String name;
    private Integer age;
    private String email;
}
