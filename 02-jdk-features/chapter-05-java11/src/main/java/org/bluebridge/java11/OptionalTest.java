package org.bluebridge.java11;

import org.junit.Test;

import java.util.Optional;

/**
 * Java11 Optional 测试
 *
 * @author lingwh
 * @date 2025/1/25 10:27
 */
public class OptionalTest {

    /**
     * java11 新增了 检测 Optional 中对象是否为空的方法
     */
    @Test
    public void testOptionalIsEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("判断Optional中对象是否存在:" + emptyOptional.isPresent());
        System.out.println("判断Optional中对象是否为空:" + emptyOptional.isEmpty());
    }
}
