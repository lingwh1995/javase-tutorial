package org.bluebridge.annotation;

/**
 * 注解的属性和属性的默认值
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public @interface Anno2 {

    // 注解的属性
    int age();

    String name();

    // 为注解的属性设置默认值
    String school() default "UFE";
}
