package org.bluebridge.cas;

import org.junit.Test;

import java.util.Arrays;

/**
 * 多条件判断简化写法
 *
 * @author lingwh
 * @date 2026/7/22 19:43
 */
public class Case_06_MultiConditionJudgeTest {

    @Test
    public void test() {
        // 普通写法
        String food = "banana";
        if (food.equals("banana") || food.equals("apple") || food.equals("orange")) {
            System.out.println("食物的种类是水果......");
        }

        // 优化写法
        if (Arrays.asList("banana", "apple", "orange").contains(food)) {
            System.out.println("食物的种类是水果......");
        }
    }
}
