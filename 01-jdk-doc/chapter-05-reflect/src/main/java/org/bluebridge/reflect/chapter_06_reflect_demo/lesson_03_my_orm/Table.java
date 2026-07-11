package org.bluebridge.reflect.chapter_06_reflect_demo.lesson_03_my_orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lingwh
 * @desc 加在类上的注解
 * @date 2026/7/9 00:00
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
