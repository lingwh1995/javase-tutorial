package org.bluebridge.section_11_jdk11.unit_05_optional;

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
 * @date 2026/08/06 14:06
 */
public class OptionalTest {

    /**
     * 测试 isEmpty() 方法: 判断 Optional 中是否不含值
     */
    @Test
    public void testIsEmpty() {
        // 创建不含值的 Optional
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("Optional.empty().isEmpty(): " + emptyOptional.isEmpty());

        // 创建含值的 Optional
        Optional<String> optionalWithValue = Optional.of("hello");
        System.out.println("Optional.of(\"hello\").isEmpty(): " + optionalWithValue.isEmpty());

        // 使用 ofNullable(null) 创建不含值的 Optional
        Optional<String> nullableOptional = Optional.ofNullable(null);
        System.out.println("Optional.ofNullable(null).isEmpty(): " + nullableOptional.isEmpty());
    }

    /**
     * 测试 isEmpty() 与 isPresent() 的对比使用
     */
    @Test
    public void testIsEmptyVsIsPresent() {
        // 含值的 Optional
        Optional<String> optional = Optional.of("java11");

        // isPresent() 判断值是否存在
        System.out.println("isPresent() 判断含值 Optional: " + optional.isPresent());
        // isEmpty() 判断值是否不存在
        System.out.println("isEmpty() 判断含值 Optional: " + optional.isEmpty());

        // 空的 Optional
        Optional<String> emptyOptional = Optional.empty();

        // isPresent() 判断值是否存在
        System.out.println("isPresent() 判断空 Optional: " + emptyOptional.isPresent());
        // isEmpty() 判断值是否不存在
        System.out.println("isEmpty() 判断空 Optional: " + emptyOptional.isEmpty());
    }

    /**
     * 测试 isEmpty() 在实际业务场景中的应用
     */
    @Test
    public void testIsEmptyInApplication() {
        // 模拟从外部获取可能为 null 的值
        String value = findUserEmail();
        Optional<String> optional = Optional.ofNullable(value);

        // 使用 isEmpty() 进行判空处理, 语义更清晰
        if (optional.isEmpty()) {
            System.out.println("用户邮箱为空, 使用默认邮箱: " + optional.orElse("default@email.com"));
        } else {
            System.out.println("用户邮箱: " + optional.get());
        }

        // 使用 isPresent() 进行条件判断, 配合 ifPresent() 消费值
        optional.ifPresent(email -> System.out.println("发送邮件到: " + email));
    }

    /**
     * 测试 isEmpty() 与 isPresent() 结合 filter 使用
     */
    @Test
    public void testIsEmptyWithFilter() {
        // 创建包含特定值的 Optional
        Optional<String> optional = Optional.of("hello");

        // 使用 isPresent() 配合 filter 过滤
        boolean isHello = optional.filter("hello"::equals).isPresent();
        System.out.println("过滤后值是否为 'hello': " + isHello);

        // 使用 isEmpty() 配合 filter 过滤
        boolean isNotHello = optional.filter("world"::equals).isEmpty();
        System.out.println("过滤后值是否不为 'world': " + isNotHello);
    }

    /**
     * 模拟从数据库查询用户邮箱, 返回 null 表示没有邮箱
     */
    private String findUserEmail() {
        // 模拟数据库查询结果为空
        return null;
    }
}