package org.bluebridge.java16;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java16 Stream 测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class StreamTest {

    /**
     * 测试 collect(Collectors.toList()) 的简写形式 toList()
     */
    @Test
    public void testStreamToList() {
        String[] fruits = { "apple", "pear", "orange" };
        // java16之前
        List<String> collect = Stream.of(fruits).filter(fruit -> fruit.length() >= 5).collect(Collectors.toList());
        System.out.println(collect);

        // java16及以后
        List<String> list = Stream.of(fruits).filter(fruit -> fruit.length() >= 5).toList();
        System.out.println(list);
    }
}
