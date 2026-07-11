package org.bluebridge.java11;

import java.util.Optional;
import org.junit.Test;

/**
 * @author lingwh
 * @desc Java11 Optional 测试
 * @date 2026/7/9 00:00
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
