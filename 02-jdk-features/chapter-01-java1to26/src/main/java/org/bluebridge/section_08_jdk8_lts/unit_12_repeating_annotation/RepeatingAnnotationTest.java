package org.bluebridge.section_08_jdk8_lts.unit_12_repeating_annotation;

import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/**
 * Java8 重复注解的反射 API 测试
 *
 * 演化历程：
 *  Java 8 之前：重复注解需要通过容器注解手动包装，反射获取时只能通过 getAnnotation() 获取容器注解，再从中取出值
 *  Java 8 及以后：提供 AnnotatedElement.getAnnotationsByType() 和
 *  AnnotatedElement.getDeclaredAnnotationsByType() 方法，可直接获取重复注解，无需手动处理容器注解
 *
 *
 * @author lingwh
 * @date 2026/08/06 10:00
 */
public class RepeatingAnnotationTest {

    /**
     * 可重复注解 - 角色
     */
    @Repeatable(Roles.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Role {
        String value();
    }

    /**
     * 容器注解 - 角色列表
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Roles {
        Role[] value();
    }

    /**
     * 多次使用重复注解 @Role
     */
    @Role("admin")
    @Role("user")
    @Role("guest")
    public class User {
    }

    /**
     * 测试：getAnnotationsByType() 获取重复注解
     */
    @Test
    public void testGetAnnotationsByType() {
        // 通过 getAnnotationsByType 直接获取重复注解数组
        Role[] roles = User.class.getAnnotationsByType(Role.class);
        System.out.println("通过 getAnnotationsByType 获取的注解: " + Arrays.toString(roles));
        for (Role role : roles) {
            System.out.println("角色: " + role.value());
        }
    }

    /**
     * 测试：getDeclaredAnnotationsByType() 获取重复注解
     */
    @Test
    public void testGetDeclaredAnnotationsByType() {
        // 通过 getDeclaredAnnotationsByType 获取本类上声明的重复注解
        Role[] roles = User.class.getDeclaredAnnotationsByType(Role.class);
        System.out.println("通过 getDeclaredAnnotationsByType 获取的注解: " + Arrays.toString(roles));
        for (Role role : roles) {
            System.out.println("角色: " + role.value());
        }
    }

    /**
     * 测试：获取容器注解
     */
    @Test
    public void testGetContainerAnnotation() {
        // 获取容器注解 Roles（重复注解的实际存储形式）
        Roles roles = User.class.getAnnotation(Roles.class);
        System.out.println("获取到容器注解: " + roles);
        // 遍历容器中的重复注解
        for (Role role : roles.value()) {
            System.out.println("容器中的角色: " + role.value());
        }
    }
}