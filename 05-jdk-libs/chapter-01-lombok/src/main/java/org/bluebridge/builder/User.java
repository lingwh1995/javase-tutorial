package org.bluebridge.builder;

import lombok.Builder;
import lombok.ToString;

/**
 * User实体，使用@Builder为类添加建造者模式(链式编程)
 *
 * @author lingwh
 * @date 2025/8/18 11:24
 */
@Builder
@ToString
public class User {

    private String id;
    private String name;
    private int age;
    private String email;
}
