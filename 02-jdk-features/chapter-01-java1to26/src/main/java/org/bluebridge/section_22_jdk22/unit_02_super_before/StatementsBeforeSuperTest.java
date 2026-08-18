package org.bluebridge.section_22_jdk22.unit_02_super_before;

import org.junit.Test;

/**
 * JDK 22 构造器前置语句测试(PREVIEW 预览特性)
 *
 * 构造器前置语句(Statements Before Super, JEP 447) 是 JDK 22 的 PREVIEW 预览特性,
 * 第二次预览。允许在构造器中 super() 或 this() 调用前执行语句,
 * 这些语句不能访问正在构造的实例(即不能访问 this 的字段和方法)。
 *
 * 使用场景:
 *   1. 在 super() 前进行参数验证
 *   2. 在 super() 前对参数进行转换或计算
 *   3. 在 super() 前准备数据
 *
 * 注意: 本文件使用 JDK 22 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 22 StatementsBeforeSuperTest.java
 *       运行命令: java --enable-preview StatementsBeforeSuperTest
 *
 * 演化历程: 构造器前置语句 JDK 22(JEP 447, 1st PREVIEW) → JDK 23(JEP 482, 2nd) → JDK 24(JEP 492, 3rd) → JDK 25(STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:10
 */
public class StatementsBeforeSuperTest {

    /**
     * 基类: 用于演示构造器前置语句
     */
    public static class Base {
        private final String value;

        public Base(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 子类: 在 super() 前执行参数验证
     */
    public static class ValidatedSub extends Base {
        public ValidatedSub(String input) {
            // JDK 22 PREVIEW 特性: 在 super() 前执行参数验证
            if (input == null || input.isBlank()) {
                throw new IllegalArgumentException("输入不能为空或空白");
            }
            // 验证通过后调用 super()
            super(input.trim());
        }
    }

    /**
     * 子类: 在 super() 前执行参数转换
     */
    public static class TransformedSub extends Base {
        public TransformedSub(String raw) {
            // JDK 22 PREVIEW 特性: 在 super() 前对参数进行转换
            String processed = raw != null ? raw.toUpperCase() : "DEFAULT";
            // 转换后调用 super()
            super(processed);
        }
    }

    /**
     * 子类: 在 super() 前执行复杂计算
     */
    public static class ComputedSub extends Base {
        public ComputedSub(String prefix, String suffix) {
            // JDK 22 PREVIEW 特性: 在 super() 前执行复杂计算
            String combined = (prefix != null ? prefix : "") + "_" + (suffix != null ? suffix : "");
            // 计算后调用 super()
            super(combined);
        }
    }

    /**
     * 测试在 super() 前执行参数验证(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 在构造器中先验证参数, 再调用 super()
     */
    @Test
    public void testValidationBeforeSuper_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 正常情况
        ValidatedSub sub = new ValidatedSub("  hello  ");
        System.out.println("验证后值: '" + sub.getValue() + "'");
        System.out.println("--------------------------------------");

        // 验证失败情况
        try {
            new ValidatedSub("   ");
            System.out.println("不应该到达这里");
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到验证异常: " + e.getMessage());
        }
    }

    /**
     * 测试在 super() 前执行参数转换(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 在构造器中先转换参数, 再调用 super()
     */
    @Test
    public void testTransformationBeforeSuper_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 转换为大写
        TransformedSub sub1 = new TransformedSub("hello");
        System.out.println("转换后值: " + sub1.getValue());

        // null 值使用默认值
        TransformedSub sub2 = new TransformedSub(null);
        System.out.println("null 默认值: " + sub2.getValue());
        System.out.println("--------------------------------------");
    }

    /**
     * 测试在 super() 前执行复杂计算(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 在构造器中先计算组合值, 再调用 super()
     */
    @Test
    public void testComputationBeforeSuper_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        ComputedSub sub = new ComputedSub("Hello", "World");
        System.out.println("组合值: " + sub.getValue());
        System.out.println("--------------------------------------");

        // 带 null 的组合
        ComputedSub sub2 = new ComputedSub(null, "JDK22");
        System.out.println("null 前缀组合值: " + sub2.getValue());
    }

    /**
     * 测试在 super() 前执行多个前置语句(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 在 super() 前执行多个语句: 验证、转换、日志等
     */
    @Test
    public void testMultipleStatementsBeforeSuper_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 使用内部类模拟多语句前置场景
        class MultiStatementSub extends Base {
            public MultiStatementSub(String raw) {
                // JDK 22 PREVIEW 特性: 多个前置语句
                // 语句1: 验证
                if (raw == null) {
                    throw new IllegalArgumentException("参数不能为 null");
                }
                // 语句2: 日志
                System.out.println("  [日志] 正在处理: " + raw);
                // 语句3: 转换
                String result = raw.trim().toUpperCase();
                // 最后调用 super()
                super(result);
            }
        }

        MultiStatementSub sub = new MultiStatementSub("  jdk 22  ");
        System.out.println("多语句前置结果: " + sub.getValue());
        System.out.println("--------------------------------------");

        // 验证异常
        try {
            new MultiStatementSub(null);
            System.out.println("不应该到达这里");
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到异常: " + e.getMessage());
        }
    }
}