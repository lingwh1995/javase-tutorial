package org.bluebridge.java12;

import org.junit.Test;

/**
 * java14 提供了更强的 switch 使用语法
 *
 * Java12 预览，Java13 改用yield，Java14 正式成为标准特性，不再需要--enable-preview。
 * - 可以使用 -> 箭头语法直接返回 switch 结果
 * - 代码块内使用 yield 返回 switch 结果
 * - 支持作为表达式返回值，不再需要 break 返回
 *
 * @author lingwh
 * @date 2025/1/25 11:08
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

    /**
     * 支持使用两种形式返回 switch 结果
     *
     * 1. 没有方法体时使用箭头函数直接返回
     * 2. 有方法体时使用 yield 关键字返回
     */
    @Test
    public void testSwitchArrowAndYieldReturn() {
        Food food = Food.RICE;
        String desc = null;
        // 测试使用箭头函数返回
        desc = switch (food) {
            // 使用箭头函数直接返回
            case RICE -> "主食...";
            // 使用 yield 关键字返回
            case APPLE, PEAR, BANANA -> {
                yield "水果...";
            }
            case MILK, WATER -> "饮料...";
            default -> throw new IllegalStateException("没有该食物...");
        };
        System.out.println(desc);

        food = Food.APPLE;
        // 测试使用 yield 关键字返回
        desc = switch (food) {
            // 使用箭头函数直接返回
            case RICE -> "主食...";
            // 使用 yield 关键字返回
            case APPLE, PEAR, BANANA -> {
                yield "水果...";
            }
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
