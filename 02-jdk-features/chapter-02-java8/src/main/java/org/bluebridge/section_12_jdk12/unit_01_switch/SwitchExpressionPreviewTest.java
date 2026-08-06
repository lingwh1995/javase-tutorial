﻿package org.bluebridge.section_12_jdk12.unit_01_switch;

import org.junit.Test;

/**
 * JDK 12 Switch 表达式（PREVIEW 特性，JEP 325）
 *      注意：JDK 12 的 switch 表达式使用 break 返回值，而不是 yield（yield 是 JDK 13 引入的）
 *      编译和执行需要 --enable-preview 参数
 *
 * @author lingwh
 * @date 2026/08/05 19:05
 */
public class SwitchExpressionPreviewTest {

    /**
     * 测试 JDK 12 箭头语法 case 标签（-> 语法）
     *      箭头语法不需要 break，不会穿透
     */
    @Test
    public void testArrowCasePreview() {
        int day = 3;
        String result = switch (day) {
            case 1 -> "周一";
            case 2 -> "周二";
            case 3 -> "周三";
            case 4 -> "周四";
            case 5 -> "周五";
            case 6 -> "周六";
            case 7 -> "周日";
            default -> "非法参数";
        };
        System.out.println("箭头语法 switch 结果: " + result);
    }

    /**
     * 测试 JDK 12 switch 表达式使用 break 返回值
     *      这是 JDK 12 引入的预览特性，用 break value; 返回结果
     */
    @Test
    public void testBreakReturnValuePreview() {
        int day = 5;
        String result = switch (day) {
            case 1: break "周一";
            case 2: break "周二";
            case 3: break "周三";
            case 4: break "周四";
            case 5: break "周五";
            case 6: break "周六";
            case 7: break "周日";
            default: break "非法参数";
        };
        System.out.println("break 返回值 switch 结果: " + result);
    }

    /**
     * 测试 switch 表达式结合枚举类型
     */
    @Test
    public void testSwitchWithEnumPreview() {
        Season season = Season.SUMMER;
        String result = switch (season) {
            case SPRING -> "春天";
            case SUMMER -> "夏天";
            case AUTUMN -> "秋天";
            case WINTER -> "冬天";
        };
        System.out.println("枚举 switch 结果: " + result);
    }

    /**
     * 测试 switch 表达式多标签匹配
     */
    @Test
    public void testMultiLabelCasePreview() {
        int month = 3;
        String season = switch (month) {
            case 3, 4, 5 -> "春季";
            case 6, 7, 8 -> "夏季";
            case 9, 10, 11 -> "秋季";
            case 12, 1, 2 -> "冬季";
            default -> "非法月份";
        };
        System.out.println("月份 " + month + " 对应的季节: " + season);
    }

    private enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }
}