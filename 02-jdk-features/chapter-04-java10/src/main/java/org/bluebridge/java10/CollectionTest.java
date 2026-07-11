package org.bluebridge.java10;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author lingwh
 * @desc java10新增创建不可变集合方式
 * @date 2026/7/9 00:00
 */
public class CollectionTest {

    /**
     * 测试使用 copyOf() 创建集合
     */
    @Test
    public void testCopyOf() {
        // 如果是只读集合，copyOf()创建的新对象和源对象内存地址是一样的
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        List<Integer> numsCopyOf = List.copyOf(nums);
        System.out.println(nums == numsCopyOf);
        System.out.println("--------------------------------------");

        // 如果不是只读集合，copyOf()创建的新对象和源对象内存地址是不一样的
        List<String> strs = new ArrayList<>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        List<String> strsCopyOf = List.copyOf(strs);
        System.out.println(strs == strsCopyOf);
        // strsCopyOf.add("d");
    }
}
