package org.bluebridge.java11;

import org.junit.Test;

import java.util.Optional;

/**
 * Java11 Optional 测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class OptionalTest {

    /**
     * java11新增了 检测Optional中对象是否为空的方法
     */
    @Test
    public void testOptionalIsEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("判断Optional中对象是否存在:" + emptyOptional.isPresent());
        System.out.println("判断Optional中对象是否为空:" + emptyOptional.isEmpty());
    }
}
