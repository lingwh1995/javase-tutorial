package org.bluebridge.section_02_collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections工具类常用方法测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class CollectionsTest {

    /**
     * 测试获取集合中的最大值
     */
    @Test
    public void testCollectionsMax() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(Collections.max(list));
    }

    /**
     * 测试获取集合中的最小值
     */
    @Test
    public void testCollectionsMin() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(Collections.min(list));
    }

    /**
     * 测试翻转集合元素顺序
     */
    @Test
    public void testCollectionsReverse() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Collections.reverse(list);
        System.out.println(list);
    }

    /**
     * 测试随机打乱集合元素顺序
     */
    @Test
    public void testCollectionsShuffle() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Collections.shuffle(list);
        System.out.println(list);
    }

    /**
     * 测试集合自然排序
     */
    @Test
    public void testCollectionsSort() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(5);
        Collections.sort(list);
        System.out.println(list);
    }
}
