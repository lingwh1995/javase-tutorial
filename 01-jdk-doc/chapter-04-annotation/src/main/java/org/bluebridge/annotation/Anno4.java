package org.bluebridge.annotation;

/**
 * @author lingwh
 * @desc 注解属性类型示例
 * @date 2026/7/9 00:00
 */
public @interface Anno4 {

    int a();

    String b();

    /**
     * 枚举类型的属性
     */
    ColorEnum c();

    Class d();

    /**
     * 注解类型的属性
     */
    Anno2 e();

    int[] f();
}
