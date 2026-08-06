package org.bluebridge.section_24_jdk24.unit_04_flexible_constructor;

import org.junit.Test;

/**
 * JDK 24 灵活构造器体测试（PREVIEW 预览特性）
 *
 * 灵活构造器体(Flexible Constructor Bodies, JEP 492) 是 JDK 24 的 PREVIEW 预览特性，
 * 第三次预览，编译和运行都需要 --enable-preview 参数。
 *
 * 在 JDK 24 之前，构造器中 super() 或 this() 调用必须是构造器体中的第一条语句。
 * 灵活构造器体允许在 super() 或 this() 调用之前执行语句，只要这些语句不读取
 * 正在构造的对象（即不访问 this）。
 *
 * 主要变化：
 *   1. 允许在 super() 之前执行初始化逻辑
 *   2. 允许在 super() 之前进行参数验证
 *   3. 允许在 super() 之前计算参数
 *   4. 仍然禁止在 super() 之前访问 this
 *
 * 注意：本文件使用 JDK 24 PREVIEW 特性的真实语法编写，
 *       编译命令：javac --enable-preview --release 24 FlexibleConstructorTest.java
 *       运行命令：java --enable-preview FlexibleConstructorTest
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class FlexibleConstructorTest {

    /**
     * 父类，用于测试灵活构造器体
     */
    public static class Parent {
        private final String value;

        public Parent(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        /**
         * 带验证的工厂方法，用于测试 super() 前的参数计算
         */
        public static String validateAndProcess(String input) {
            if (input == null || input.isBlank()) {
                return "default";
            }
            return input.trim();
        }
    }

    /**
     * 子类-传统方式：在 super() 之前进行参数验证（PREVIEW）
     * 传统方式只能在 super() 调用之后进行验证，而灵活构造器体允许在 super() 之前验证
     */
    public static class ChildWithValidation extends Parent {

        /**
         * 使用灵活构造器体：在 super() 之前验证参数
         * JDK 24 PREVIEW 特性，需要 --enable-preview
         */
        public ChildWithValidation(String name) {
            // 在 super() 之前执行参数验证（这是 JDK 24 的新特性）
            if (name == null) {
                throw new IllegalArgumentException("name 不能为 null");
            }
            // 验证通过后调用 super()
            super(name);
        }
    }

    /**
     * 子类-参数预处理：在 super() 之前计算参数（PREVIEW）
     * 在 super() 之前对参数进行预处理和转换
     */
    public static class ChildWithPreprocessing extends Parent {

        /**
         * 使用灵活构造器体：在 super() 之前预处理参数
         * JDK 24 PREVIEW 特性，需要 --enable-preview
         */
        public ChildWithPreprocessing(String rawInput) {
            // 在 super() 之前预处理参数（这是 JDK 24 的新特性）
            String processed = (rawInput == null || rawInput.isBlank()) ? "陌生人" : rawInput.trim();
            // 使用预处理后的值调用 super()
            super(processed);
        }
    }

    /**
     * 子类-日志记录：在 super() 之前记录日志（PREVIEW）
     * 在 super() 之前执行日志记录操作
     */
    public static class ChildWithLogging extends Parent {

        private static int instanceCount = 0;

        /**
         * 使用灵活构造器体：在 super() 之前记录日志
         * JDK 24 PREVIEW 特性，需要 --enable-preview
         */
        public ChildWithLogging(String name) {
            // 在 super() 之前记录日志（这是 JDK 24 的新特性）
            System.out.println("  [日志] 正在创建 ChildWithLogging 实例, name = " + name);
            // 调用 super()
            super(name);
            // super() 之后也可以执行语句
            instanceCount++;
            System.out.println("  [日志] 实例创建完成, 当前实例数: " + instanceCount);
        }
    }

    /**
     * 子类-多步计算：在 super() 之前进行复杂计算（PREVIEW）
     * 在 super() 之前执行多步计算逻辑
     */
    public static class ChildWithComputation extends Parent {

        /**
         * 使用灵活构造器体：在 super() 之前进行复杂计算
         * JDK 24 PREVIEW 特性，需要 --enable-preview
         */
        public ChildWithComputation(String firstName, String lastName) {
            // 在 super() 之前进行多步计算（这是 JDK 24 的新特性）
            String fullName = (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
            fullName = fullName.trim().isEmpty() ? "未知" : fullName.trim();
            // 调用 super()
            super(fullName);
        }
    }

    /**
     * 测试在 super() 之前进行参数验证（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testValidationBeforeSuper_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        // 测试正常情况
        Parent p = new ChildWithValidation("张三");
        System.out.println("验证通过: " + p.getValue());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试在 super() 之前进行参数预处理（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 在 super() 之前对参数进行空值处理和去除前后空格
     */
    @Test
    public void testPreprocessingBeforeSuper_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        // 测试空字符串处理
        Parent p1 = new ChildWithPreprocessing("  李四  ");
        System.out.println("预处理后: '" + p1.getValue() + "'");
        System.out.println("--- 分割线 ---");

        // 测试 null 值处理
        Parent p2 = new ChildWithPreprocessing(null);
        System.out.println("null 处理后: '" + p2.getValue() + "'");
        System.out.println("--- 分割线 ---");

        // 测试空白字符串处理
        Parent p3 = new ChildWithPreprocessing("   ");
        System.out.println("空白字符串处理后: '" + p3.getValue() + "'");
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试在 super() 前后执行日志记录（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 在 super() 之前记录日志，super() 之后也记录日志
     */
    @Test
    public void testLoggingBeforeAndAfterSuper_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        System.out.println("创建第一个实例:");
        Parent p1 = new ChildWithLogging("王五");
        System.out.println("  值: " + p1.getValue());
        System.out.println("--- 分割线 ---");

        System.out.println("创建第二个实例:");
        Parent p2 = new ChildWithLogging("赵六");
        System.out.println("  值: " + p2.getValue());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试在 super() 之前进行多步计算（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 将 firstName 和 lastName 组合成 fullName 后再调用 super()
     */
    @Test
    public void testComputationBeforeSuper_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        // 两个参数都有值
        Parent p1 = new ChildWithComputation("张", "三丰");
        System.out.println("组合结果: '" + p1.getValue() + "'");
        System.out.println("--- 分割线 ---");

        // lastName 为 null
        Parent p2 = new ChildWithComputation("李四", null);
        System.out.println("部分 null 结果: '" + p2.getValue() + "'");
        System.out.println("--- 分割线 ---");

        // 两个参数都为 null
        Parent p3 = new ChildWithComputation(null, null);
        System.out.println("全 null 结果: '" + p3.getValue() + "'");
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试传统方式与灵活构造器体对比（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 对比传统方式（只能在 super() 后验证）和灵活构造器体（可在 super() 前验证）
     */
    @Test
    public void testTraditionalVsFlexible_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        // 灵活构造器体：在 super() 之前进行验证和预处理
        try {
            // 验证失败的情况
            Parent p = new ChildWithValidation(null);
            System.out.println("创建成功: " + p.getValue());
        } catch (IllegalArgumentException e) {
            System.out.println("灵活构造器体验证: null 参数被正确拦截, 异常信息: " + e.getMessage());
        }
        System.out.println("--- 分割线 ---");

        // 灵活构造器体：在 super() 之前预处理 null 值
        Parent p = new ChildWithPreprocessing(null);
        System.out.println("灵活构造器体预处理: null 被转换为默认值 '" + p.getValue() + "'");
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试灵活构造器体中的 this 访问限制（演示说明）
     * 注意：在 super() 之前不能访问 this 对象，否则编译错误
     * 下面的代码仅作为演示说明，实际编译会报错
     */
    @Test
    public void testThisAccessRestriction() {
        System.out.println("灵活构造器体限制说明:");
        System.out.println("  1. super() 之前不能访问 this 引用");
        System.out.println("  2. super() 之前不能调用本类的实例方法");
        System.out.println("  3. super() 之前只能执行与 this 无关的语句");
        System.out.println("  4. 允许调用静态方法、静态字段、局部变量计算");
        System.out.println("  5. 允许进行参数验证、日志记录、参数预处理");
        System.out.println("--- 分割线 ---");
    }
}