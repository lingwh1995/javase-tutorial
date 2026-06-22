package org.bluebridge.java8.chapter_06_collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

/**
 * @author lingwh
 * @desc
 * @date 2025/12/2 11:18
 */

/**
 * Map.computeIfAbsent() 工作机制
 *    触发条件：当指定的 key 在 map 中不存在或者对应的值为 null 时执行
 *    核心功能：用于初始化键值对，如果键不存在则计算并添加新值
 *    参数传递：将 key 作为参数传递给映射函数 Function<? super K, ? extends V>
 *    典型应用：集合初始化、缓存场景
 * Map.computeIfPresent() 工作机制
 *    触发条件：当指定的 key 在 map 中存在且对应的值不为 null 时执行
 *    核心功能：用于更新已存在的键值对
 *    参数传递：将 key 和当前值作为参数传递给重映射函数 BiFunction<? super K, ? super V, ? extends V>
 *    典型应用：条件更新、计数器操作
 */
@Slf4j
public class MapTest {

    /**
     * 集合初始化场景 ：初始化集合时，使用 computeIfAbsent 创建集合
     */
    @Test
    public void testComputeIfAbsentForCollections() {
        Map<String, List<String>> userMap = new HashMap<>();
        log.info("原始值map: {}", userMap);

        // 传统方式
        /*
        if(userMap.containsKey("admin")) {
            userMap.put("employee", new ArrayList<>());
        }
        if(userMap.containsKey("employee")) {
            userMap.put("employee", new ArrayList<>());
        }
        userMap.put("admin", Arrays.asList("超级管理员1", "超级管理员2"));
        userMap.put("employee", Arrays.asList("雇员1", "雇员2"));
        */

        // 使用 computeIfAbsent 简化代码
        userMap.computeIfAbsent("admin", k -> Arrays.asList("超级管理员1", "超级管理员2"));
        userMap.computeIfAbsent("employee", k -> Arrays.asList("雇员1", "雇员2"));
        log.info("更新后的map: {}", userMap);
    }

    /**
     * 缓存场景：使用 computeIfAbsent 实现缓存，避免重复计算
     */
    @Test
    public void testComputeIfAbsentCacheScenario() {
        // 模拟缓存Map
        Map<String, Integer> cache = new HashMap<>();

        // 定义耗时的计算函数，传入一个String类型参数，返回一个Integer类型结果
        Function<String, Integer> expensiveCalculation = key -> {
            log.info("执行耗时计算: 计算 {} 的平方...", key);
            try {
                // 模拟耗时操作
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return Integer.parseInt(key) * Integer.parseInt(key);
        };

        log.info("=== 第一次访问 ===");
        Integer result1 = cache.computeIfAbsent("5", expensiveCalculation);
        log.info("计算结果: {}", result1);

        log.info("=== 第二次访问 ===");
        Integer result2 = cache.computeIfAbsent("5", expensiveCalculation);
        log.info("缓存结果: {}", result2);

        log.info("=== 缓存内容 ===");
        log.info("当前缓存: {}", cache);
    }

    /**
     * 条件更新场景：使用 computeIfPresent 更新存在的键值对的学生分数
     */
    @Test
    public void testComputeIfPresentForConditionalUpdate() {
        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("Alice", 85);
        scoreMap.put("Bob", 92);
        // 注意这个null值
        scoreMap.put("Charlie", null);

        log.info("原始分数: {}", scoreMap);

        // 为存在的学生加分
        scoreMap.computeIfPresent("Alice", (name, score) -> score + 5);
        scoreMap.computeIfPresent("Bob", (name, score) -> score + 3);

        // 尝试为不存在的键更新（不会生效）
        scoreMap.computeIfPresent("David", (name, score) -> score + 10);

        // 尝试为null值更新（不会生效）
        scoreMap.computeIfPresent("Charlie", (name, score) -> score + 5);

        log.info("更新后分数: {}", scoreMap);
        // 输出: {Alice=90, Bob=95, Charlie=null}
    }

    /**
     * 计数器场景：使用 computeIfPresent 实现销售前后库存统计
     */
    @Test
    public void testComputeIfPresentForCounter() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        inventory.put("apple", 10);
        inventory.put("banana", 0);
        inventory.put("orange", 5);

        log.info("初始库存: {}", inventory);

        // 销售商品，减少库存
        String itemToSell = "apple";
        inventory.computeIfPresent(itemToSell, (item, count) -> {
            if (count > 0) {
                int newCount = count - 1;
                log.info("销售 {}，剩余库存: {}", item, newCount);
                return newCount;
            }
            return 0; // 库存不足时不改变
        });

        // 尝试销售缺货商品
        inventory.computeIfPresent("banana", (item, count) -> {
            if (count > 0) {
                return count - 1;
            }
            log.info("{} 已售罄", item);
            return 0;
        });

        log.info("最终库存: {}", inventory);
    }

}
