package org.bluebridge.section_07_jdk7.unit_08_other;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * JDK 7 其他新特性测试
 *
 * @author lingwh
 * @date 2026/08/05 19:03
 */
public class OtherFeaturesTest {

    /**
     * 测试泛型类型推断改进
     * JDK 7 中，通过方法调用可以推断泛型类型
     */
    @Test
    public void testTypeInference() {
        // JDK 7 之前：需要显式指定类型
        // List<String> list = Collections.<String>emptyList();
        // JDK 7 改进：编译器可以通过左侧声明推断泛型类型
        List<String> list = Collections.emptyList();
        System.out.println("类型推断: " + list);

        // 方法返回类型推断
        String value = getValue("hello");
        System.out.println("方法返回类型推断: " + value);

        // 泛型方法调用时的类型推断
        Integer result = add(1, 2);
        System.out.println("泛型方法类型推断: " + result);
    }

    /**
     * 测试 java.util.Objects 工具类
     */
    @Test
    public void testObjectsUtil() {
        // Objects.requireNonNull：检查对象是否为空，为空则抛出 NullPointerException
        String str = "hello";
        String nonNull = Objects.requireNonNull(str, "str 不能为 null");
        System.out.println("requireNonNull: " + nonNull);

        // 测试 requireNonNull 抛出异常
        try {
            Objects.requireNonNull(null, "参数不能为 null");
        } catch (NullPointerException e) {
            System.out.println("requireNonNull 捕获异常: " + e.getMessage());
        }

        // Objects.equals：安全的 equals 比较，避免空指针
        String a = "hello";
        String b = "hello";
        String c = null;
        boolean isEqual1 = Objects.equals(a, b);  // true
        boolean isEqual2 = Objects.equals(a, c);  // false
        boolean isEqual3 = Objects.equals(c, c);  // true（两个 null 视为相等）
        System.out.println("Objects.equals(\"hello\", \"hello\"): " + isEqual1);
        System.out.println("Objects.equals(\"hello\", null): " + isEqual2);
        System.out.println("Objects.equals(null, null): " + isEqual3);

        // Objects.hash：为多个对象生成哈希码
        int hash = Objects.hash("hello", "world", 123);
        System.out.println("Objects.hash 结果: " + hash);

        // Objects.toString：安全的 toString，避免空指针
        System.out.println("Objects.toString(null): " + Objects.toString(null));
        System.out.println("Objects.toString(null, \"默认值\"): " + Objects.toString(null, "默认值"));

        // Objects.compare：安全比较，null 会排在前面
        int compareResult = Objects.compare("a", "b", String::compareTo);
        System.out.println("Objects.compare(\"a\", \"b\"): " + compareResult);
    }

    /**
     * 测试 java.util.concurrent.ThreadLocalRandom
     */
    @Test
    public void testThreadLocalRandom() {
        // 获取当前线程的 ThreadLocalRandom 实例
        ThreadLocalRandom random = ThreadLocalRandom.current();

        // 生成随机整数
        int randomInt = random.nextInt();
        System.out.println("随机整数: " + randomInt);

        // 生成指定范围的随机整数
        int randomIntRange = random.nextInt(1, 100);
        System.out.println("1-100 之间的随机整数: " + randomIntRange);

        // 生成随机长整数
        long randomLong = random.nextLong();
        System.out.println("随机长整数: " + randomLong);

        // 生成指定范围的随机长整数
        long randomLongRange = random.nextLong(1000, 10000);
        System.out.println("1000-10000 之间的随机长整数: " + randomLongRange);

        // 生成随机浮点数
        double randomDouble = random.nextDouble();
        System.out.println("随机浮点数: " + randomDouble);

        // 生成指定范围的随机双精度浮点数
        double randomDoubleRange = random.nextDouble(10.0, 20.0);
        System.out.println("10.0-20.0 之间的随机浮点数: " + randomDoubleRange);

        // 生成随机布尔值
        boolean randomBoolean = random.nextBoolean();
        System.out.println("随机布尔值: " + randomBoolean);

        // ThreadLocalRandom 相比 Random 的优势：每个线程有独立的随机数生成器，
        // 减少了线程间竞争，适合高并发场景
    }

    /**
     * 测试传统单 catch 的异常处理对比
     * 注意：这里展示的是 JDK 7 中对异常重抛的改进效果
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTraditionalExceptionHandling() {
        // 模拟条件判断
        String input = "invalid";
        if (!"valid".equals(input)) {
            throw new IllegalArgumentException("输入无效: " + input);
        }
    }

    /**
     * 辅助方法：测试类型推断
     */
    @SuppressWarnings("unchecked")
    private static <T> T getValue(Object value) {
        return (T) value;
    }

    /**
     * 辅助方法：测试泛型方法类型推断
     */
    private static <T extends Number> T add(T a, T b) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() + b.intValue());
        }
        if (a instanceof Long) {
            return (T) Long.valueOf(a.longValue() + b.longValue());
        }
        if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() + b.doubleValue());
        }
        throw new UnsupportedOperationException("不支持的数值类型");
    }
}