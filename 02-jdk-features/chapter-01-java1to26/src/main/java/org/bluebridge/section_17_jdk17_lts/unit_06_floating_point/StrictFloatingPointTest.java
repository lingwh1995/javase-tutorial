package org.bluebridge.section_17_jdk17_lts.unit_06_floating_point;

import org.junit.Test;

/**
 * JDK 17 恢复严格浮点（JEP 306，STANDARD 正式特性）测试
 *
 * 演化历程：
 *   JDK 1.0~1.1：默认严格浮点，所有浮点运算严格遵循 IEEE 754 标准
 *   JDK 1.2：引入 strictfp 关键字，默认浮点运算不再严格（允许中间结果使用扩展精度）
 *   JDK 17（JEP 306）：恢复严格浮点为默认行为，始终严格遵循 IEEE 754 标准
 *     strictfp 关键字仍然保留，但不再产生任何效果
 *
 * JEP 306 核心变化：
 *   1. 所有浮点运算默认严格遵循 IEEE 754 标准
 *   2. strictfp 关键字保留但语义变为空操作（no-op）
 *   3. 消除了 strictfp / 非 strictfp 的差异，简化编程模型
 *   4. 增强浮点运算的可预测性和可移植性
 *
 * @author lingwh
 * @date 2026/08/06 18:19
 */
public class StrictFloatingPointTest {

    /**
     * 测试浮点运算的基本精度
     * 演示在 JDK 17 中，浮点运算始终严格遵循 IEEE 754 标准
     */
    @Test
    public void testBasicFloatingPointPrecision() {
        System.out.println("=== 基本浮点运算精度测试 ===");

        // 经典浮点精度问题：0.1 + 0.2 != 0.3
        double a = 0.1;
        double b = 0.2;
        double sum = a + b;
        System.out.println("0.1 + 0.2 = " + sum);
        System.out.println("0.1 + 0.2 == 0.3 ? " + (sum == 0.3));
        System.out.println("预期结果：0.1 + 0.2 = 0.30000000000000004（IEEE 754 标准行为）");

        // 浮点乘法
        double c = 1.0 / 3.0;
        System.out.println("1.0 / 3.0 = " + c);
        System.out.println("1.0 / 3.0 * 3.0 = " + (c * 3.0));
        System.out.println("预期结果：1.0/3.0*3.0 = 0.9999999999999999（IEEE 754 标准行为）");

        // 大数相加
        double large = 1.0e16;
        double small = 1.0;
        System.out.println("1.0e16 + 1.0 = " + (large + small));
        System.out.println("预期结果：1.0e16 + 1.0 = 1.0e16（精度损失，IEEE 754 标准行为）");
    }

    /**
     * 测试 strictfp 关键字在 JDK 17 中的行为
     * JDK 17 中 strictfp 仍然是合法关键字，但语义变为空操作（no-op）
     */
    @Test
    public void testStrictfpKeyword() {
        System.out.println("=== strictfp 关键字在 JDK 17 中的行为 ===");

        // 普通方法中的浮点运算
        double result1 = normalMethod();
        System.out.println("普通方法结果：" + result1);

        // strictfp 方法中的浮点运算
        double result2 = strictfpMethod();
        System.out.println("strictfp 方法结果：" + result2);

        // JDK 17 中两者结果完全相同
        System.out.println("结果是否相同：" + (result1 == result2));
        System.out.println("说明：JDK 17 中 strictfp 是空操作，所有浮点运算默认严格遵循 IEEE 754");
    }

    /**
     * 测试 strictfp 类的行为
     * strictfp 可以应用于类，使其所有方法中的浮点运算都是严格的
     */
    @Test
    public void testStrictfpClass() {
        System.out.println("=== strictfp 类在 JDK 17 中的行为 ===");

        StrictfpCalculator calculator = new StrictfpCalculator();
        double sum = calculator.add(0.1, 0.2);
        double product = calculator.multiply(1.0, 3.0);
        double quotient = calculator.divide(1.0, 3.0);

        System.out.println("strictfp 类中的浮点运算：");
        System.out.println("  0.1 + 0.2 = " + sum);
        System.out.println("  1.0 * 3.0 = " + product);
        System.out.println("  1.0 / 3.0 = " + quotient);

        // 与非 strictfp 类对比
        NormalCalculator normalCalculator = new NormalCalculator();
        double normalSum = normalCalculator.add(0.1, 0.2);
        System.out.println("非 strictfp 类中的结果：" + normalSum);
        System.out.println("两者结果是否相同：" + (sum == normalSum));
    }

    /**
     * 测试浮点运算的可预测性
     * JDK 17 恢复严格浮点后，浮点运算在不同平台上的结果一致
     */
    @Test
    public void testFloatingPointPredictability() {
        System.out.println("=== 浮点运算可预测性测试 ===");

        // 复杂浮点运算：向量点积
        double[] vectorA = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] vectorB = {0.1, 0.2, 0.3, 0.4, 0.5};

        double dotProduct = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
        }
        System.out.println("向量点积结果：" + dotProduct);
        System.out.println("预期结果：1.0*0.1 + 2.0*0.2 + 3.0*0.3 + 4.0*0.4 + 5.0*0.5 = 5.5");
        System.out.println("差异：" + Math.abs(dotProduct - 5.5));

        // 浮点运算的关联性（不满足结合律）
        double x = 1.0e16;
        double y = -1.0e16;
        double z = 1.0;
        // (x + y) + z
        double result1 = (x + y) + z;
        // x + (y + z)
        double result2 = x + (y + z);
        System.out.println("浮点运算结合律测试：");
        System.out.println("  (1e16 + (-1e16)) + 1.0 = " + result1);
        System.out.println("  1e16 + ((-1e16) + 1.0) = " + result2);
        System.out.println("  结果是否相等：" + (result1 == result2));
        System.out.println("说明：浮点运算不满足结合律，这是 IEEE 754 标准行为");
    }

    /**
     * 测试特殊浮点值
     * NaN、Infinity、-0.0 等特殊值的处理
     */
    @Test
    public void testSpecialFloatingPointValues() {
        System.out.println("=== 特殊浮点值测试 ===");

        // NaN（Not a Number）
        double nan = 0.0 / 0.0;
        System.out.println("0.0 / 0.0 = " + nan);
        System.out.println("NaN == NaN ? " + (nan == nan));
        System.out.println("Double.isNaN(NaN) ? " + Double.isNaN(nan));

        // Infinity
        double positiveInfinity = 1.0 / 0.0;
        double negativeInfinity = -1.0 / 0.0;
        System.out.println("1.0 / 0.0 = " + positiveInfinity);
        System.out.println("-1.0 / 0.0 = " + negativeInfinity);
        System.out.println("Double.isInfinite(Infinity) ? " + Double.isInfinite(positiveInfinity));

        // -0.0
        double negativeZero = -1.0 / Double.POSITIVE_INFINITY;
        System.out.println("-1.0 / Infinity = " + negativeZero);
        System.out.println("0.0 == -0.0 ? " + (0.0 == negativeZero));
        System.out.println("Double.compare(0.0, -0.0) = " + Double.compare(0.0, negativeZero));

        // 特殊值运算
        System.out.println("NaN + 1.0 = " + (nan + 1.0));
        System.out.println("Infinity + Infinity = " + (positiveInfinity + positiveInfinity));
        System.out.println("Infinity + (-Infinity) = " + (positiveInfinity + negativeInfinity));
    }

    /**
     * 测试 strictfp 在接口中的行为
     * strictfp 可用于接口中的方法声明
     */
    @Test
    public void testStrictfpInterface() {
        System.out.println("=== strictfp 接口在 JDK 17 中的行为 ===");

        // 匿名实现类
        StrictfpOperation operation = new StrictfpOperation() {
            @Override
            public double calculate(double a, double b) {
                return a * a + b * b;
            }
        };

        double result = operation.calculate(3.0, 4.0);
        System.out.println("strictfp 接口方法计算结果：3.0^2 + 4.0^2 = " + result);
        System.out.println("预期结果：25.0");
        System.out.println("JDK 17 中 strictfp 接口方法与普通接口方法行为一致");
    }

    /**
     * 测试 JEP 306 带来的变化说明
     * 对比 JDK 1.2~16 和 JDK 17 的浮点运算行为差异
     */
    @Test
    public void testJep306Changes() {
        System.out.println("=== JEP 306 恢复严格浮点（STANDARD）===");
        System.out.println("JEP 306 在 JDK 17 中转正为 STANDARD 正式特性");
        System.out.println();

        System.out.println("1. 背景：");
        System.out.println("   JDK 1.2 引入 strictfp 关键字后，默认浮点运算允许中间结果");
        System.out.println("   使用 80 位扩展精度（x87 FPU），导致不同平台结果不一致");
        System.out.println();

        System.out.println("2. 变化内容：");
        System.out.println("   - 所有浮点运算默认严格遵循 IEEE 754 标准");
        System.out.println("   - 中间结果不再使用扩展精度，统一使用 32/64 位");
        System.out.println("   - strictfp 关键字保留但语义为空操作（no-op）");
        System.out.println();

        System.out.println("3. 影响范围：");
        System.out.println("   - float、double 及对应包装类的运算");
        System.out.println("   - Math 类中的三角函数、指数函数等");
        System.out.println("   - 使用 FP-strict 模式的所有代码");
        System.out.println();

        System.out.println("4. 优势：");
        System.out.println("   - 跨平台浮点运算结果一致，增强可移植性");
        System.out.println("   - 简化编程模型，不再需要关心 strictfp 关键字");
        System.out.println("   - 提高浮点运算的可预测性和可测试性");
        System.out.println();

        System.out.println("5. 兼容性：");
        System.out.println("   - 大部分现有代码不受影响（差异仅在极端情况下可观察到）");
        System.out.println("   - strictfp 关键字仍然可以编译通过，但无实际效果");
        System.out.println("   - 少数依赖扩展精度的代码可能产生略微不同的结果");
    }

    /**
     * 测试 x87 FPU 扩展精度与 IEEE 754 的差异对比
     * 演示 JDK 17 消除的扩展精度问题
     */
    @Test
    public void testExtendedPrecisionDifference() {
        System.out.println("=== x87 扩展精度与 IEEE 754 对比 ===");

        // 复杂的浮点运算序列，在 JDK 1.2~16 中可能因扩展精度产生不同结果
        // 在 JDK 17 中，结果始终确定且一致
        double result = computeComplexExpression();
        System.out.println("复杂浮点表达式结果：" + result);

        // 在不同的求值顺序下验证结果一致性
        double result2 = computeComplexExpressionDifferentOrder();
        System.out.println("不同求值顺序的结果：" + result2);
        System.out.println("结果是否一致：" + (result == result2));

        System.out.println();
        System.out.println("说明：JDK 17 中所有浮点运算结果都是确定性的，");
        System.out.println("不依赖于底层硬件（x87/SSE2）的精度差异。");
    }

    /**
     * 普通方法：非 strictfp
     */
    private double normalMethod() {
        double a = 1.0e16;
        double b = 1.0;
        double c = -1.0e16;
        return (a + b) + c;
    }

    /**
     * strictfp 方法：JDK 17 中与普通方法无异
     */
    private strictfp double strictfpMethod() {
        double a = 1.0e16;
        double b = 1.0;
        double c = -1.0e16;
        return (a + b) + c;
    }

    /**
     * 计算复杂浮点表达式
     */
    private double computeComplexExpression() {
        double sum = 0.0;
        for (int i = 1; i <= 100; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    /**
     * 以不同顺序计算复杂浮点表达式
     */
    private double computeComplexExpressionDifferentOrder() {
        double sum = 0.0;
        for (int i = 100; i >= 1; i--) {
            sum += 1.0 / i;
        }
        return sum;
    }

    /**
     * strictfp 接口：JDK 17 中与普通接口行为一致
     */
    strictfp interface StrictfpOperation {
        double calculate(double a, double b);
    }

    /**
     * strictfp 类：JDK 17 中所有方法默认严格浮点
     */
    strictfp static class StrictfpCalculator {
        public double add(double a, double b) {
            return a + b;
        }

        public double multiply(double a, double b) {
            return a * b;
        }

        public double divide(double a, double b) {
            return a / b;
        }
    }

    /**
     * 普通类：与 StrictfpCalculator 行为完全一致
     */
    static class NormalCalculator {
        public double add(double a, double b) {
            return a + b;
        }

        public double multiply(double a, double b) {
            return a * b;
        }

        public double divide(double a, double b) {
            return a / b;
        }
    }
}
