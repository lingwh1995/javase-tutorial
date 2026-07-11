package org.bluebridge.java8.chapter_06_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc List集合removeIf方法测试
 * @date 2025/12/2 11:23
 */
@Slf4j
public class ListTest {

    /**
     * 删除列表中长度大于5的元素
     */
    @Test
    public void testRemoveIf_1() {
        List<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
        log.info("删除前: {}", list);
        // 删除长度大于5的元素
        boolean removed = list.removeIf(s -> s.length() > 5);
        log.info("是否删除了元素: {}", removed);
        log.info("删除后: {}", list);
    }

    /**
     * 删除列表中长度大于5的元素，且找出被删除的元素
     */
    @Test
    public void testRemoveIf_2() {
        List<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
        log.info("原始集合: {}", list);

        // 定义删除条件
        Predicate<String> filter = s -> s.length() > 5;

        // 获取被删除的元素
        List<String> removedElements = list.stream().filter(filter).collect(Collectors.toList());

        // 获取保留的元素
        List<String> remainingElements =
                list.stream().filter(filter.negate()).collect(Collectors.toList());

        log.info("删除后的集合: {}", remainingElements);
        log.info("被删除的元素: {}", removedElements);
    }
}
