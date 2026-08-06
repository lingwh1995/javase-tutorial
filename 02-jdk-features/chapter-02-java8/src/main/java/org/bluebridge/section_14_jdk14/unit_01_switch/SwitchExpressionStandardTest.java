﻿package org.bluebridge.section_14_jdk14.unit_01_switch;

import org.junit.Test;

/**
 * Java14 Switch 表达式测试(STANDARD 正式特性)
 *
 * Switch 表达式演进历程:
 * 1. JDK12(JEP 325): 以 PREVIEW 特性引入箭头语法 case 标签
 * 2. JDK13(JEP 354): 改进 PREVIEW 特性, 引入 yield 关键字返回结果
 * 3. JDK14(JEP 361): 转正成为 STANDARD 正式特性, 不再需要 --enable-preview
 *
 * Switch 表达式核心语法:
 * 1. 箭头语法 case 标签(case 1 ->): 无需 break, 不会发生穿透
 * 2. 每个 case 支持多个常量(case 1, 2, 3 ->)
 * 3. 使用 yield 在代码块中返回结果
 * 4. switch 表达式可以作为返回值直接赋值给变量
 *
 * 说明: 本文件使用真实的 Switch 表达式语法(JDK 14 转正为标准特性), 需要 JDK 14+ 才能编译
 *
 * @author lingwh
 * @date 2026/08/05 18:31
 */
public class SwitchExpressionStandardTest {

    /**
     * 测试 Switch 表达式箭头语法 case 标签(case 1 ->)
     * 箭头语法每个 case 只能跟一个表达式, 自动 break, 不会发生穿透
     */
    @Test
    public void testSwitchExpressionArrowCase() {
        int day = 2;
        // Switch 表达式(箭头语法): case 1 -> 自动 break, 不会发生穿透
        String arrowResult = switch (day) {
            case 1 -> "周一";
            case 2 -> "周二";
            case 3 -> "周三";
            default -> "未知";
        };
        System.out.println("Switch 表达式(箭头语法)结果: " + arrowResult);
    }

    /**
     * 测试 Switch 表达式每个 case 多个常量(case 1, 2, 3 ->)
     * 多个常量使用逗号分隔, 等价于传统写法中多个 case 标签并列
     */
    @Test
    public void testSwitchExpressionMultipleConstants() {
        int day = 6;
        // Switch 表达式(多个常量): case 1, 2, 3, 4, 5 -> 一次性匹配多个值
        String multiResult = switch (day) {
            case 1, 2, 3, 4, 5 -> "工作日";
            case 6, 7 -> "周末";
            default -> "非法参数";
        };
        System.out.println("Switch 表达式(多个常量)结果: " + multiResult);
    }

    /**
     * 测试 Switch 表达式使用 yield 返回值
     * 当 case 分支需要执行多条语句时, 使用代码块 + yield 返回结果
     */
    @Test
    public void testSwitchExpressionYield() {
        int month = 2;
        // Switch 表达式(yield): 代码块中通过 yield 返回结果
        String yieldResult = switch (month) {
            case 12, 1, 2 -> "冬季";
            case 3, 4, 5 -> "春季";
            case 6, 7, 8 -> "夏季";
            case 9, 10, 11 -> "秋季";
            // 代码块中通过 yield 返回结果
            default -> {
                System.out.println("非法月份: " + month);
                yield "非法月份";
            }
        };
        System.out.println("Switch 表达式(yield)结果: " + yieldResult);
    }

    /**
     * 测试 Switch 表达式作为返回值
     * switch 表达式可以直接赋值给变量, 也可以作为方法的返回值
     */
    @Test
    public void testSwitchExpressionAsReturnValue() {
        String fruit = "APPLE";
        // switch 表达式直接赋值给变量
        String category = switch (fruit) {
            case "APPLE", "PEAR", "BANANA" -> "水果";
            case "RICE", "NOODLE" -> "主食";
            default -> "未知";
        };
        System.out.println("Switch 表达式(赋值)结果: " + category);

        // switch 表达式作为方法返回值
        System.out.println("Switch 表达式(方法返回)结果: " + getCategoryWithSwitchExpression(fruit));
    }

    /**
     * 使用 Switch 表达式实现分类(JDK 14 标准特性, 作为方法返回值)
     */
    private String getCategoryWithSwitchExpression(String fruit) {
        return switch (fruit) {
            case "APPLE", "PEAR", "BANANA" -> "水果";
            case "RICE", "NOODLE" -> "主食";
            default -> "未知";
        };
    }
}
