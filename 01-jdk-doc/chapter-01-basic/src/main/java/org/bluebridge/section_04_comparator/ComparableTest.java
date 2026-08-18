package org.bluebridge.section_04_comparator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Comparable 内部比较器测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ComparableTest {

    /**
     * 测试使用内部比较器接口 Comparable 对集合进行排序
     */
    @Test
    public void testCollectionComparable() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("a", 10));
        personList.add(new Person("b", 10));
        personList.add(new Person("c", 10));

        // 打印 list 的原始序列
        System.out.printf("原始序列： %s\n", personList.toString());

        // 使用内部比较器接口 Comparable<String> 进行排序
        Collections.sort(personList);
        System.out.printf("按名字进行排序(内部比较器接口Comparable)： %s\n", personList.toString());
    }
}
