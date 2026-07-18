package org.bluebridge.equals_and_hashcode;

import lombok.EqualsAndHashCode;

/**
 * User实体，使用@EqualsAndHashCode注解自动生成equals和hashCode方法
 *
 * @author lingwh
 * @date 2025/8/18 11:45
 */
@EqualsAndHashCode
public class User {

    private String id;
    private String name;
    private int age;
    private String email;
}
