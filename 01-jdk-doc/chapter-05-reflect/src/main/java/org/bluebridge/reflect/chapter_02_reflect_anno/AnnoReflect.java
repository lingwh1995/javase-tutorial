package org.bluebridge.reflect.chapter_02_reflect_anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 反射应用在注解上:
 *      1.注解的保留策略必须是RUNTIME
 *      2.反射注解需要从作用目标上返回
 *          类上的注解:需要使用Class来获取
 *          方法上的注解:需要使用Method来获取
 *          构造器上的注解:需要使用Contrucator来获取
 *          成员上的注解:需要使用Filed来获取
 *      Class
 *          Method、Constructor、Field 的父类:AccessibleObject
 *      它们都有一个方法：
 *          Annotation getAnnotation(Class)，返回目标上指定类型的注解！
 *          Annotation[] getAnnotations()，返回目标上所有注解！
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