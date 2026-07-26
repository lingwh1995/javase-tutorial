package org.bluebridge.java10;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * java10 新增创建不可变集合方式
 *
 * @author lingwh
 * @date 2025/1/25 14:46
 */
public class CollectionTest {

    /**
     * 测试使用 copyOf() 创建集合
     */
    @Test
    public void testCopyOf() {
        // 如果是只读集合，copyOf() 创建的新对象和源对象内存地址是一样的
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        List<Integer> numsCopyOf = List.copyOf(nums);
        System.out.println(nums == numsCopyOf);
        System.out.println("--------------------------------------");

        // 如果不是只读集合，copyOf() 创建的新对象和源对象内存地址是不一样的
        List<String> strs = new ArrayList<>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        List<String> strsCopyOf = List.copyOf(strs);
        System.out.println(strs == strsCopyOf);
        // strsCopyOf.add("d");
    }
}
