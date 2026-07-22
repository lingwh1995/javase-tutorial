package org.bluebridge.reflect.section_02_reflect_anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 反射读取注解示例
 *
 * @author lingwh
 * @date 2026/6/22 18:04
 */
@MyAnno2(name = "zhangsan", age = 20, sex = "nv")
public class AnnoReflect {

    @MyAnno2(name = "lisi", age = 30, sex = "nan")
    private String filed;

    @MyAnno2(name = "wangwu", age = 40, sex = "nan")
    @MyAnno3(name = "zhaoliu", age = 50)
    public void eat() {
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno2 {

    String name();

    int age();

    String sex();
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno3 {

    String name();

    int age();
}
