package org.bluebridge.cas;

import com.google.common.collect.Sets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 使用多种方式判断list中元素是否重复
 * @date 2026/7/10 00:00
 */
@Slf4j
public class _01_ListDuplicateTest {

    @Test
    public void testListDuplicate() {
        List<Integer> list = Arrays.asList(1, 2, 3, 3, 4, 5);
        log.debug("hasDuplicate(list): {}", hasDuplicate(list));
        log.debug("hasDuplicateByHashSet(list): {}", hasDuplicateByHashSet(list));
        log.debug("hasDuplicateByStream(list): {}", hasDuplicateByStream(list));
        log.debug("hasDuplicateByTreeSet(list): {}", hasDuplicateByTreeSet(list));
        log.debug("hasDuplicateByBitSet(list): {}", hasDuplicateByBitSet(list));
        log.debug("hasDuplicateByGuava(list): {}", hasDuplicateByGuava(list));
        log.debug("hasDuplicateByCommons(list): {}", hasDuplicateByCommons(list));
    }

    /**
     * 1.暴力双循环法（基础实现方案）
     * 分析
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicate(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 2.HashSet法（基础实现方案）
     * 优化点
     *    初始容量设置为list.size()避免扩容
     *    快速失败机制
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicateByHashSet(List<?> list) {
        Set<Object> set = new HashSet<>(list.size());
        for (Object item : list) {
            // add返回false表示存在重复
            if (!set.add(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 3.Stream API实现（进阶实现方案）
     *
     * 特征
     *    代码简洁
     *    支持并行处理
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicateByStream(List<?> list) {
        return list.stream().distinct().count() < list.size();
    }

    /**
     * 4.TreeSet排序法（进阶实现方案）
     * 适用场景
     *    需要自然排序结果
     *    元素实现Comparable接口
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicateByTreeSet(List<?> list) {
        Set<Object> set = new TreeSet<>(list);
        return set.size() < list.size();
    }

    /**
     * 5.并行流处理（高性能优化方案）
     * 优势
     *    利用多核CPU加速
     *    线程安全的并发集合
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicateParallel(List<?> list) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        // 用于判断流中是否存在任意一个元素满足给定的条件（Predicate）。若存在则返回true，否则返回false‌。
        return list.parallelStream().anyMatch(e -> !seen.add(e));
    }

    /**
     * 6.BitSet位图法（高性能优化方案）
     * 限制
     *    仅适用于正整数
     *    内存占用与最大数值相关
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicateByBitSet(List<Integer> list) {
        BitSet bitSet = new BitSet();
        for (Integer num : list) {
            if (bitSet.get(num))
                return true;
            bitSet.set(num);
        }
        return false;
    }

    /**
     * 7.Guava工具类
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicateByGuava(List<?> list) {
        return Sets.newHashSet(list).size() < list.size();
    }

    /**
     * 8.Apache commons-collections
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicateByCommons(List<?> list) {
        return CollectionUtils.getCardinalityMap(list).values().stream()
                .anyMatch(count -> (int) count > 1);
    }
}
