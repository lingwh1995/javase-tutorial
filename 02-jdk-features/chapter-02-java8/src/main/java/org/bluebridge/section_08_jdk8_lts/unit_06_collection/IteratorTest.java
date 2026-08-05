package org.bluebridge.section_08_jdk8_lts.unit_06_collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.*;

/**
 * 迭代器测试
 *
 * @author lingwh
 * @date 2026/6/17 10:01
 */
public class IteratorTest {

    @Test
    public void iteratorTest() throws JsonProcessingException {
        /**
         * forEachRemaining() 遍历集合
         */
        List<String> list = Arrays.asList("Java", "Jackson", "Mysql", "Redis");
        Iterator<String> iterator = list.iterator();
        // forEachRemaining() 取出所有元素 - lambda 表达式版
        iterator.forEachRemaining(item -> System.out.println(item));
        // forEachRemaining() 取出所有元素 - 方法引用版
        // iterator.forEachRemaining(System.out::println);
        System.out.println("-----------------------");

        /**
         * forEachRemaining() 获取 JsonNode 中所有 key，再通过 key 获取 value
         */
        String jsonStr = "{ \"id\":1001, \"name\":\"张三\", \"age\":22, \"address\":\"西安\" }";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonStr);
        if (root.isObject()) {
            Iterator<String> fieldNames = root.fieldNames();
            List<String> keys = new ArrayList<>();

            // 将所有顶层 key 收集到集合
            fieldNames.forEachRemaining(keys::add);
            System.out.printf("JSON顶层所有key：%s\n", keys);

            // 重新获取迭代器，遍历打印 key 和 value
            System.out.println("\n遍历键值对：");
            root.fieldNames().forEachRemaining(key -> {
                JsonNode value = root.get(key);
                System.out.printf("key=%s, value=%s\n", key, value);
            });
        }
        System.out.println("-----------------------");

        /**
         * forEachRemaining() 同时获取 JsonNode 中 key、value
         */
        JsonNode node = mapper.readTree(jsonStr);
        if (node.isObject()) {
            node.fields().forEachRemaining((Map.Entry<String, JsonNode> entry) -> {
                String key = entry.getKey();
                JsonNode val = entry.getValue();
                System.out.printf("key -> %s\n", val.asText());
            });
        }
        System.out.println("-----------------------");

        /**
         * forEachRemaining() 过滤集合元素
         */
        List<Integer> nums = Arrays.asList(1, 12, 33, 45, 6, 88);
        Iterator<Integer> it = nums.iterator();
        List<Integer> bigNum = new ArrayList<>();

        // 只收集大于 10 的数字
        it.forEachRemaining(num -> {
            if (num > 10) {
                bigNum.add(num);
            }
        });
        System.out.println("大于 10 的数字：" + bigNum);
    }
}
