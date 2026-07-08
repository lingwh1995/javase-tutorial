package org.bluebridge.chapter_04_set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试Set集合
 */
@Slf4j
public class SetTest {

    /**
     * 添加成功返回true,添加失败返回false
     */
    @Test
    public void testSetAdd() {
        Set<Integer> nums = new HashSet<>();
        // 添加新元素可以直接添加成功，返回true
        log.debug("nums.add(0): {}", nums.add(0));   // true
        // 添加新元素可以直接添加成功，返回true
        log.debug("nums.add(1): {}", nums.add(1));   // true
        // 添加新元素可以直接添加成功，返回true
        log.debug("nums.add(2): {}", nums.add(2));   // true
        log.debug("nums: {}", nums);
        // 添加已有元素不能添加成功，返回false
        log.debug("nums.add(0): {}", nums.add(0));   // false
    }

}
