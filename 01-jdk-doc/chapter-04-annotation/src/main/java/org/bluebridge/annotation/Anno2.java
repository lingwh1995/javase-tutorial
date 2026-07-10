package org.bluebridge.annotation;

/**
 * @author lingwh
 * @desc 注解的属性和属性的默认值
 * @date 2026/7/9 00:00
 */
public @interface Anno2 {

    // 注解的属性
    int age();

    String name();

    // 为注解的属性设置默认值
    String school() default "UFE";
}
