package org.bluebridge.java12;

import org.junit.Test;

/**
 * java12 提供了更强的 switch 使用语法
 *
 * @author lingwh
 * @date 2026/7/9 10:30
 */
public class SwitchTest {

    /**
     * 配合 lambda 表达式使用
     */
    @Test
    public void testSwitchWithLambda() {
        Food food = Food.RICE;
        switch (food) {
            case RICE -> System.out.println("主食...");
            case APPLE, PEAR, BANANA -> System.out.println("水果...");
            case MILK, WATER -> System.out.println("饮料...");
            default -> new IllegalStateException("没有该食物...");
        }
    }

    /**
     * 有返回值的 Return
     */
    @Test
    public void testSwitchHasReturn() {
        Food food = Food.RICE;
        String desc = null;
        desc = switch (food) {
            case RICE -> "主食...";
            case APPLE, PEAR, BANANA -> "水果...";
            case MILK, WATER -> "饮料...";
            default -> throw new IllegalStateException("没有该食物...");
        };
        System.out.println(desc);
    }
}

enum Food {
    RICE,
    APPLE,
    PEAR,
    BANANA,
    MILK,
    WATER
}
