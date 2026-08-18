package org.bluebridge.section_17_jdk17.unit_04_random_generator;

import org.junit.Test;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * JDK 17 新的随机数生成器接口测试(STANDARD 正式特性)
 *
 * 随机数生成器接口(JEP 356) 在 JDK 17 中成为 STANDARD 正式特性:
 *   1. RandomGenerator 接口: 统一了所有随机数生成器的 API, 提供 nextInt()、nextLong()、nextDouble()、
 *      ints()、longs()、doubles() 等方法
 *   2. RandomGeneratorFactory: 通过工厂按算法名称创建随机数生成器, 并可查看算法属性
 *   3. 新增多种高性能算法: L128X128MixRandom、L128X256MixRandom、L64X128MixRandom、
 *      L64X256MixRandom、L64X1024MixRandom、Xoshiro256PlusPlus、Xoroshiro128PlusPlus 等
 *      (原有 Random、SecureRandom、SplittableRandom 也统一实现了 RandomGenerator 接口)
 *
 * 演化历程: 随机数生成器 JDK 17 STANDARD（JEP 356）
 *
 * @author lingwh
 * @date 2026/08/05 18:46
 */
public class RandomGeneratorTest {

    /**
     * 测试 RandomGenerator 接口的基本用法(STANDARD)
     * 通过 RandomGeneratorFactory.getDefault() 获取默认算法 L64X128MixRandom 的生成器
     */
    @Test
    public void testRandomGeneratorBasic() {
        // 获取默认随机数生成器(JEP 356 引入), 默认算法为 L64X128MixRandom
        RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();
        System.out.println("默认生成器类名: " + randomGenerator.getClass().getName());
        System.out.println("--------------------------------------");
        // RandomGenerator 接口统一提供的基本方法
        System.out.println("nextInt() = " + randomGenerator.nextInt());
        System.out.println("nextLong() = " + randomGenerator.nextLong());
        System.out.println("nextDouble() = " + randomGenerator.nextDouble());
        System.out.println("nextBoolean() = " + randomGenerator.nextBoolean());
        // 带边界的方法: [0, bound) 区间
        System.out.println("nextInt(100) = " + randomGenerator.nextInt(100));
        // 指定区间的方法: [origin, bound) 区间
        System.out.println("nextInt(10, 20) = " + randomGenerator.nextInt(10, 20));
    }

    /**
     * 测试 RandomGeneratorFactory 工厂创建指定算法(STANDARD)
     * 通过 RandomGenerator.of(算法名) 或 RandomGeneratorFactory.of(算法名).create() 创建指定算法的生成器
     */
    @Test
    public void testRandomGeneratorFactory() {
        // RandomGenerator.of(算法名): 直接创建指定算法的生成器
        RandomGenerator l64x128MixRandom = RandomGenerator.of("L64X128MixRandom");
        System.out.println("RandomGenerator.of(\"L64X128MixRandom\") 生成随机数: " + l64x128MixRandom.nextInt());
        // RandomGeneratorFactory.of(算法名).create(): 先取工厂再创建
        RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of("Random");
        RandomGenerator random = factory.create();
        System.out.println("RandomGeneratorFactory.of(\"Random\") 生成随机数: " + random.nextInt());
        System.out.println("--------------------------------------");
        // 工厂属性: 算法名称、分组、状态位数、是否可拆分/可流式化
        System.out.println("算法名称: " + factory.name());
        System.out.println("算法分组: " + factory.group());
        System.out.println("状态位数: " + factory.stateBits());
        System.out.println("是否可拆分: " + factory.isSplittable());
        System.out.println("是否可流式化: " + factory.isStreamable());
        System.out.println("是否可跳跃: " + factory.isJumpable());
    }

    /**
     * 测试遍历所有可用的随机数生成算法(STANDARD)
     * RandomGeneratorFactory.all() 返回所有算法工厂的流, 可以查看 JDK 17 提供的全部算法
     */
    @Test
    public void testAllRandomGeneratorAlgorithms() {
        // 遍历并排序展示所有可用算法
        System.out.println("JDK 17 可用的随机数生成算法: ");
        RandomGeneratorFactory.all()
                .sorted((f1, f2) -> f1.name().compareTo(f2.name()))
                .forEach(factory -> System.out.printf("算法: %-24s 分组: %-16s 状态位数: %d%n",
                        factory.name(), factory.group(), factory.stateBits()));
    }

    /**
     * 测试 RandomGenerator 生成随机数流(STANDARD)
     * ints()、longs()、doubles() 可以生成指定数量的随机数流(Stream)
     */
    @Test
    public void testRandomGeneratorStreams() {
        RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();
        // ints(5): 生成 5 个随机 int
        System.out.print("ints(5): ");
        randomGenerator.ints(5).forEach(i -> System.out.print(i + " "));
        System.out.println();
        // ints(5, 1, 100): 生成 5 个 [1, 100) 区间的随机 int
        System.out.print("ints(5, 1, 100): ");
        randomGenerator.ints(5, 1, 100).forEach(i -> System.out.print(i + " "));
        System.out.println();
        // longs(5): 生成 5 个随机 long
        System.out.print("longs(5): ");
        randomGenerator.longs(5).forEach(l -> System.out.print(l + " "));
        System.out.println();
        // doubles(5): 生成 5 个随机 double
        System.out.print("doubles(5): ");
        randomGenerator.doubles(5).forEach(d -> System.out.printf("%.4f ", d));
        System.out.println();
    }
}
