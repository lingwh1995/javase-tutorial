package org.bluebridge.section_13_jdk13.unit_01_switch;

import org.junit.Test;

/**
 * JDK 13 Switch 表达式(PREVIEW 特性)测试
 *
 * 1. 箭头语法 case 标签: case 1 -> 写法, 自动 break, 不会发生穿透
 * 2. 每个 case 多个常量: case 1, 2, 3 -> 写法, 多个常量使用逗号分隔
 * 3. 使用 yield 返回值: 箭头右侧为代码块时, 通过 yield 返回 switch 表达式的计算结果
 * 4. JDK 13 中 switch 表达式属于 PREVIEW 特性(JEP 354), 需要 javac --enable-preview 编译,
 *    java --enable-preview 运行, 否则无法编译
 *    - JDK 12(JEP 325, PREVIEW): 首次引入箭头语法 case 标签, 使用 break value 返回结果
 *    - JDK 13(JEP 354, PREVIEW): 将 break value 改为 yield 返回结果
 *    - JDK 14(JEP 361): switch 表达式转正为标准特性, 不再需要 --enable-preview
 *
 * @author lingwh
 * @date 2026/08/05 18:29
 */
public class SwitchExpressionTest {

    /**
     * 测试 Switch 表达式(PREVIEW): 箭头语法 case 标签(case 1 ->)
     * JDK 13 PREVIEW 特性, 需要 --enable-preview 编译运行
     */
    @Test
    public void testSwitchExpressionArrowCase_Preview() {
        int day = 2;
        // 真实 Switch 表达式(箭头语法): 箭头右侧直接返回结果, 自动 break, 不会发生穿透
        String arrowResult = switch (day) {
            case 1 -> "周一";
            case 2 -> "周二";
            case 3 -> "周三";
            default -> "未知";
        };
        System.out.println("Switch 表达式(箭头语法)结果: " + arrowResult);
        System.out.println("--------------------------------------");
        // 遍历多个值, 验证各个 case 分支
        for (int i = 1; i <= 4; i++) {
            String result = switch (i) {
                case 1 -> "周一";
                case 2 -> "周二";
                case 3 -> "周三";
                default -> "未知";
            };
            System.out.println("day = " + i + " -> " + result);
        }
    }

    /**
     * 测试 Switch 表达式(PREVIEW): 每个 case 多个常量(case 1, 2, 3 ->)
     * JDK 13 PREVIEW 特性, 需要 --enable-preview 编译运行
     */
    @Test
    public void testSwitchExpressionMultipleConstants_Preview() {
        int day = 6;
        // 真实 Switch 表达式(多常量 case): 多个常量使用逗号分隔, 等价于传统写法中多个 case 标签并列
        String multiResult = switch (day) {
            case 1, 2, 3, 4, 5 -> "工作日";
            case 6, 7 -> "周末";
            default -> "未知的星期: " + day;
        };
        System.out.println("Switch 表达式(多常量 case)结果: " + multiResult);
        System.out.println("--------------------------------------");
        // 遍历一周七天, 验证多常量 case 分支
        for (int i = 1; i <= 7; i++) {
            String result = switch (i) {
                case 1, 2, 3, 4, 5 -> "工作日";
                case 6, 7 -> "周末";
                default -> "未知的星期: " + i;
            };
            System.out.println("day = " + i + " -> " + result);
        }
    }

    /**
     * 测试 Switch 表达式(PREVIEW): 使用 yield 返回值
     * JDK 13 PREVIEW 特性, 需要 --enable-preview 编译运行
     */
    @Test
    public void testSwitchExpressionYield_Preview() {
        int month = 4;
        // 真实 Switch 表达式(yield): 箭头右侧为代码块时, 必须使用 yield 返回结果
        String yieldResult = switch (month) {
            case 3, 4, 5 -> "春季";
            case 6, 7, 8 -> "夏季";
            case 9, 10, 11 -> "秋季";
            case 12, 1, 2 -> "冬季";
            // 箭头右侧为代码块时, 通过 yield 返回结果
            default -> {
                System.out.println("未知月份: " + month);
                yield "未知月份: " + month;
            }
        };
        System.out.println("Switch 表达式(yield)结果: " + yieldResult);
        System.out.println("--------------------------------------");
        // 遍历 12 个月验证各季节分支, 非法月份 0 走 default 分支
        for (int i = 0; i <= 12; i++) {
            String result = switch (i) {
                case 3, 4, 5 -> "春季";
                case 6, 7, 8 -> "夏季";
                case 9, 10, 11 -> "秋季";
                case 12, 1, 2 -> "冬季";
                default -> {
                    System.out.println("未知月份: " + i);
                    yield "未知月份: " + i;
                }
            };
            System.out.println("month = " + i + " -> " + result);
        }
    }
}
