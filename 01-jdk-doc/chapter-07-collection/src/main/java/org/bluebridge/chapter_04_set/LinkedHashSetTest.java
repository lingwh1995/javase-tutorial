package org.bluebridge.chapter_04_set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 哈希保证元素唯一性
 * 链表保证元素有序
 * @author ronin
 */
@Slf4j
public class LinkedHashSetTest {

    /**
     * 测试LinkedHashSet
     *  1.有序性‌：通过内部的双向链表维护元素插入顺序，遍历时保证与插入顺序一致
     *  2.唯一性‌：继承自HashSet，确保元素不重复（基于equals()和hashCode()判断）
     * ‌底层实现‌：基于LinkedHashMap，结合哈希表（快速查找）和双向链表（维护顺序）
     */
    @Test
    public void testLinkedHashSet() {
        Set<String> set = new LinkedHashSet<>();
        set.add("a");
        set.add("b");
        set.add("a");
        set.add("c");
        set.add("b");
        set.add("d");

        for(String s:set){
            log.debug("s: {}", s);
        }
    }

}
