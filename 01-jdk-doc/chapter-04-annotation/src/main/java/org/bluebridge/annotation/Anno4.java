package org.bluebridge.annotation;

/**
 * 注解属性类型示例
 *
 * @author lingwh
 * @date 2026/4/23 16:29
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
