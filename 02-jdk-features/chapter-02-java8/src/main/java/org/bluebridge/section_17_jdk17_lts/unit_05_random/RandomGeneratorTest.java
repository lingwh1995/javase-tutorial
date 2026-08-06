package org.bluebridge.section_17_jdk17_lts.unit_05_random;

import org.junit.Test;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

/**
 * JDK 17 随机数生成器测试(JEP 356, RandomGenerator 接口)
 *
 * JEP 356 引入了 RandomGenerator 接口和 RandomGeneratorFactory, 统一了随机数生成器的 API。
 * 主要改进:
 * 1. RandomGenerator 接口: 所有随机数生成器的统一父接口, 提供 nextInt()、nextLong()、nextDouble() 等方法
 * 2. RandomGeneratorFactory: 用于创建 RandomGenerator 实例的工厂类, 支持按算法名称查找
 * 3. 多种随机算法: L128X256MixRandom、L128X128MixRandom、L64X128MixRandom 等新算法
 * 4. 新算法提供更好的统计特性、周期更长、性能更好
 *
 * 常用算法:
 * - L128X256MixRandom: 高安全性, 周期 2^256
 * - L64X128StarStarRandom: 高性能, 适合大多数场景
 * - Random: 传统算法(兼容旧代码)
 * - SecureRandom: 加密安全随机数
 *
 * @author lingwh
 * @date 2026/08/05 19:12
 */
public class RandomGeneratorTest {

    /**
     * 测试 RandomGenerator 的基本用法: 使用默认算法生成随机数
     * RandomGenerator.of("Random") 使用传统 Random 算法
     */
    @Test
    public void testRandomGeneratorBasic() {
        // 使用默认算法创建 RandomGenerator
        RandomGenerator random = RandomGenerator.of("Random");

        System.out.println("基本随机数生成:");
        System.out.println("  nextInt(): " + random.nextInt());
        System.out.println("  nextInt(100): " + random.nextInt(100));
        System.out.println("  nextLong(): " + random.nextLong());
        System.out.println("  nextDouble(): " + random.nextDouble());
        System.out.println("  nextBoolean(): " + random.nextBoolean());
        System.out.println("  nextFloat(): " + random.nextFloat());
    }

    /**
     * 测试 RandomGeneratorFactory 列出所有可用的随机算法
     * 通过 RandomGeneratorFactory.all() 遍历所有注册的算法
     */
    @Test
    public void testRandomGeneratorFactoryListAlgorithms() {
        System.out.println("JDK 17 支持的随机数算法:");
        System.out.println("========================================");

        // 遍历所有可用的随机数生成器工厂
        RandomGeneratorFactory.all()
                .sorted((f1, f2) -> f1.name().compareTo(f2.name()))
                .forEach(factory -> {
                    System.out.printf("  算法: %-25s 分组: %s%n",
                            factory.name(),
                            factory.group());
                });
        System.out.println("========================================");
    }

    /**
     * 测试 RandomGeneratorFactory 的详细信息
     * 显示每个算法的状态位数、周期等信息
     */
    @Test
    public void testRandomGeneratorFactoryDetails() {
        String[] algorithms = { "L128X256MixRandom", "L64X128StarStarRandom", "Random", "SecureRandom" };

        for (String algorithm : algorithms) {
            RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of(algorithm);
            System.out.println("算法: " + algorithm);
            System.out.println("  分组: " + factory.group());
            System.out.println("  状态位数: " + factory.stateBits());
            System.out.println("  等距: " + factory.isEquidistributed());
            System.out.println("  硬件辅助: " + factory.isHardware());
            System.out.println("  可统计: " + factory.isStatistical());
            System.out.println("  可随机跳跃: " + factory.isStochastic());
            System.out.println("  可跳跃: " + factory.isJumpable());
            System.out.println("  可分裂: " + factory.isSplittable());
            System.out.println("  可流式: " + factory.isStreamable());
            System.out.println();
        }
    }

    /**
     * 测试 L128X256MixRandom 算法(JDK 17 新增的高质量随机算法)
     * L128X256MixRandom 具有 2^256 的超长周期, 适合高质量随机数需求
     */
    @Test
    public void testL128X256MixRandom() {
        // 使用 L128X256MixRandom 算法
        RandomGenerator random = RandomGeneratorFactory.of("L128X256MixRandom").create();

        System.out.println("L128X256MixRandom 算法生成的随机数:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  随机整数 " + (i + 1) + ": " + random.nextInt(1000));
        }
    }

    /**
     * 测试 L64X128StarStarRandom 算法(JDK 17 新增的高性能随机算法)
     * L64X128StarStarRandom 在保持良好统计特性的同时提供高性能
     */
    @Test
    public void testL64X128StarStarRandom() {
        // 使用 L64X128StarStarRandom 算法
        RandomGenerator random = RandomGeneratorFactory.of("L64X128StarStarRandom").create();

        System.out.println("L64X128StarStarRandom 算法生成的随机数:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  随机浮点数 " + (i + 1) + ": " + random.nextDouble(100.0));
        }
    }

    /**
     * 测试 RandomGenerator 的流式操作: 生成随机数流
     * RandomGenerator 支持 ints()、longs()、doubles() 等流式方法
     */
    @Test
    public void testRandomGeneratorStream() {
        RandomGenerator random = RandomGeneratorFactory.of("L64X128StarStarRandom").create();

        System.out.println("随机数流(前 10 个):");
        // 生成无限随机数流, 限制为 10 个
        random.ints(10, 1, 100)
                .forEach(value -> System.out.print(value + " "));
        System.out.println();

        System.out.println("随机 doubles 流(前 5 个):");
        random.doubles(5, 0.0, 1.0)
                .forEach(value -> System.out.printf("%.4f ", value));
        System.out.println();
    }

    /**
     * 测试 RandomGenerator 的骰子模拟: 使用不同算法模拟掷骰子
     */
    @Test
    public void testRandomGeneratorDiceRoll() {
        String[] algorithms = { "Random", "L64X128StarStarRandom", "L128X256MixRandom" };

        for (String algorithm : algorithms) {
            RandomGenerator random = RandomGeneratorFactory.of(algorithm).create();
            System.out.println("算法 [" + algorithm + "] 模拟掷骰子 10 次:");
            for (int i = 0; i < 10; i++) {
                int dice = random.nextInt(1, 7); // 1 到 6
                System.out.print(dice + " ");
            }
            System.out.println();
        }
    }

    /**
     * 测试 RandomGenerator 的范围随机数生成
     * 使用 nextInt(bound)、nextLong(bound)、nextDouble(origin, bound) 生成指定范围的随机数
     */
    @Test
    public void testRandomGeneratorRange() {
        RandomGenerator random = RandomGeneratorFactory.of("L64X128StarStarRandom").create();

        System.out.println("指定范围的随机数生成:");
        System.out.println("  nextInt(1, 7): 模拟骰子 = " + random.nextInt(1, 7));
        System.out.println("  nextLong(1000, 9999): 验证码 = " + random.nextLong(1000, 9999));
        System.out.println("  nextDouble(0.0, 1.0): 概率 = " + String.format("%.4f", random.nextDouble(0.0, 1.0)));
        System.out.println("  nextGaussian(): 高斯分布 = " + String.format("%.4f", random.nextGaussian()));
    }

    /**
     * 测试 RandomGenerator 创建随机字节数组
     * 使用 nextBytes(byte[]) 方法填充随机字节
     */
    @Test
    public void testRandomGeneratorBytes() {
        RandomGenerator random = RandomGeneratorFactory.of("L128X256MixRandom").create();

        // 生成 16 字节随机数(可用于生成简单 token)
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);

        // 转换为十六进制字符串
        StringBuilder hex = new StringBuilder();
        for (byte b : randomBytes) {
            hex.append(String.format("%02x", b));
        }
        System.out.println("随机字节(16 字节)的十六进制表示: " + hex.toString());
        System.out.println("长度: " + randomBytes.length + " 字节");
    }
}