package org.bluebridge.java9.section_05_optional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Java9 Optional 测试
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public class OptionalTest {

    /**
     * java9 中 Optional 提供了一个将 Optional 对象转换为 Stream 流的方法
     */
    @Test
    public void testOptionalStream() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Optional<ArrayList<String>> listOptional = Optional.ofNullable(list);
        Stream<ArrayList<String>> stream = listOptional.stream();
        stream.flatMap(x -> x.stream()).forEach(System.out::println);
    }
}
