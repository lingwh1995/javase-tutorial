﻿package org.bluebridge.section_11_jdk11.unit_04_collection;

import org.junit.Test;

import java.util.Optional;

/**
 * Java11 Optional 新增方法测试
 *
 * Java11 为 Optional 新增了 isEmpty() 方法, 用于判断 Optional 中是否不含值,
 * 与 isPresent() 方法互为补充, 配合使用可以使代码语义更加清晰:
 * 1. isEmpty(): Java11 新增, 判断 Optional 中是否不含值
 * 2. isPresent(): 判断 Optional 中是否包含值
 *
 * @author lingwh
 * @date 2026/08/05 18:23
 */
public class CollectionImprovementsTest {

    /**
     * 测试 Optional.isEmpty() 方法: 判断 Optional 中是否不含值
     */
    @Test
    public void testOptionalIsEmpty() {
        // 创建不含值的 Optional
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("emptyOptional.isEmpty(): " + emptyOptional.isEmpty());
        // 创建含值的 Optional
        Optional<String> optional = Optional.of("hello");
        System.out.println("optional.isEmpty(): " + optional.isEmpty());
    }

    /**
     * 测试 Optional.isPresent() 方法: 判断 Optional 中是否包含值
     */
    @Test
    public void testOptionalIsPresent() {
        // Optional.empty() 创建不含值的 Optional
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("emptyOptional.isPresent(): " + emptyOptional.isPresent());
        // ofNullable(null) 创建不含值的 Optional
        Optional<String> optionalOfNullable = Optional.ofNullable(null);
        System.out.println("Optional.ofNullable(null).isPresent(): " + optionalOfNullable.isPresent());
        // 创建含值的 Optional
        Optional<String> optionalWithValue = Optional.ofNullable("hello");
        System.out.println("Optional.ofNullable(\"hello\").isPresent(): " + optionalWithValue.isPresent());
        // 配合 ifPresent() 消费值
        optionalWithValue.ifPresent(System.out::println);
    }

    /**
     * 测试 isEmpty()/isPresent() 在实际场景中的应用
     */
    @Test
    public void testOptionalApplication() {
        // 模拟从外部获取一个可能为 null 的值
        String value = getValue();
        Optional<String> optional = Optional.ofNullable(value);
        // Java11 之前只能使用 isPresent() 判断值是否存在
        if (optional.isPresent()) {
            System.out.println("值不为空: " + optional.get());
        }
        // Java11 之后可以使用 isEmpty() 判断, 语义更加清晰
        if (optional.isEmpty()) {
            System.out.println("值为空, 使用默认值: " + optional.orElse("default"));
        }
    }

    /**
     * 模拟获取一个可能为 null 的值
     */
    private String getValue() {
        return null;
    }
}
