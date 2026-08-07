package org.bluebridge.section_08_jdk8_lts.unit_10_repeatable_annotation;

import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Java8 新增 @Repeatable 重复注解（JEP 120）
 *
 * 演化历程：
 *  Java 8 之前：同一位置只能使用一次同种注解，否则需要显式编写容器注解
 *  Java 8 及以后：通过 @Repeatable 元注解，允许在同一位置重复使用同种注解，简化了多注解的编写方式
 *
 * @author lingwh
 * @date 2026/08/06 10:00
 */
public class RepeatableAnnotationTest {

    /**
     * 可重复注解 - 日程
     */
    @Repeatable(Schedules.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Schedule {
        String value();
    }

    /**
     * 容器注解 - 日程列表
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Schedules {
        Schedule[] value();
    }

    /**
     * 多次使用重复注解
     */
    @Schedule("起床")
    @Schedule("吃早饭")
    @Schedule("去上班")
    @Schedule("写代码")
    @Schedule("吃午饭")
    public void myDailyRoutine() {
    }

    /**
     * 测试：通过反射获取重复注解
     */
    @Test
    public void testGetRepeatableAnnotations() throws NoSuchMethodException {
        // 通过 getAnnotationsByType 获取重复注解（无需感知容器注解的存在）
        Schedule[] schedules = RepeatableAnnotationTest.class
                .getMethod("myDailyRoutine")
                .getAnnotationsByType(Schedule.class);
        // 遍历输出所有日程
        for (Schedule schedule : schedules) {
            System.out.println("日程: " + schedule.value());
        }
    }

    /**
     * 测试：通过反射获取容器注解
     */
    @Test
    public void testGetContainerAnnotation() throws NoSuchMethodException {
        // 获取容器注解 Schedules
        Schedules schedules = RepeatableAnnotationTest.class
                .getMethod("myDailyRoutine")
                .getAnnotation(Schedules.class);
        // 遍历容器中的重复注解
        for (Schedule schedule : schedules.value()) {
            System.out.println("容器中的日程: " + schedule.value());
        }
    }
}