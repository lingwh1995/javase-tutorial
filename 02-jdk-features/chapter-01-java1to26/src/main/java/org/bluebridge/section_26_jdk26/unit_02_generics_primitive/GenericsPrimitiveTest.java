package org.bluebridge.section_26_jdk26.unit_02_generics_primitive;

import org.junit.Test;

/**
 * JDK 26 基本类型泛型测试(PREVIEW 预览特性)
 *
 * 基本类型泛型(Universal Generics / Primitive Types in Generics) 在 JDK 26
 * 中作为 PREVIEW 预览特性引入。传统 Java 泛型只支持引用类型作为类型参数,
 * 如 {@code List<Integer>}, 需要装箱/拆箱操作。JDK 26 允许在泛型中直接
 * 使用基本类型作为类型参数, 如 {@code List<int>}, 避免装箱开销。
 *
 * 注意: 该特性在 JDK 26 为 PREVIEW 预览特性,
 *       编译和运行都需要 --enable-preview 参数。
 *
 * 演化历程: 基本类型泛型 JDK 26(1st PREVIEW)
 *
 * @author lingwh
 * @date 2026/08/06 09:11
 */
public class GenericsPrimitiveTest {

    /**
     * 测试基本类型在 List 泛型中的使用(PREVIEW)
     * JDK 26 PREVIEW 特性，需要 --enable-preview
     * 可以直接使用 List<int> 而不需要 List<Integer>
     * 避免了装箱/拆箱的性能开销
     */
    @Test
    public void testPrimitiveList_Preview() {
        // JDK 26 PREVIEW 特性，需要 --enable-preview
        // 基本类型 int 直接作为泛型类型参数
        java.util.List<int> numbers = new java.util.ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        // 无需拆箱, 直接获取基本类型值
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            int num = numbers.get(i);
            sum += num;
        }
        System.out.println("List<int> 元素之和: " + sum);
        System.out.println("--------------------------------------");

        // 传统写法对比: List<Integer> 需要装箱/拆箱
        java.util.List<Integer> boxedNumbers = new java.util.ArrayList<>();
        boxedNumbers.add(10);  // 自动装箱
        boxedNumbers.add(20);
        boxedNumbers.add(30);
        int boxedSum = 0;
        for (int i = 0; i < boxedNumbers.size(); i++) {
            boxedSum += boxedNumbers.get(i);  // 自动拆箱
        }
        System.out.println("List<Integer> 元素之和: " + boxedSum);
    }

    /**
     * 测试多种基本类型在泛型中的使用(PREVIEW)
     * JDK 26 PREVIEW 特性，需要 --enable-preview
     * 支持 int, long, double, boolean 等基本类型作为泛型参数
     */
    @Test
    public void testMultiplePrimitiveGenerics_Preview() {
        // JDK 26 PREVIEW 特性，需要 --enable-preview
        // long 基本类型作为泛型参数
        java.util.List<long> longList = new java.util.ArrayList<>();
        longList.add(10000000000L);
        longList.add(20000000000L);
        System.out.println("List<long> 第一个元素: " + longList.get(0));

        // double 基本类型作为泛型参数
        java.util.List<double> doubleList = new java.util.ArrayList<>();
        doubleList.add(3.14159);
        doubleList.add(2.71828);
        System.out.println("List<double> 第一个元素: " + doubleList.get(0));

        // boolean 基本类型作为泛型参数
        java.util.List<boolean> booleanList = new java.util.ArrayList<>();
        booleanList.add(true);
        booleanList.add(false);
        System.out.println("List<boolean> 第一个元素: " + booleanList.get(0));
    }

    /**
     * 测试基本类型泛型在方法中的使用(PREVIEW)
     * JDK 26 PREVIEW 特性，需要 --enable-preview
     * 泛型方法支持基本类型参数推断
     */
    @Test
    public void testPrimitiveGenericMethod_Preview() {
        // JDK 26 PREVIEW 特性，需要 --enable-preview
        // 泛型方法使用基本类型
        int max = maxOf(10, 20, 30);
        System.out.println("int 最大值: " + max);
        System.out.println("--------------------------------------");

        double maxDouble = maxOf(3.14, 2.71, 1.41);
        System.out.println("double 最大值: " + maxDouble);
    }

    // 泛型辅助方法, 查找三个值中的最大值
    private static <T extends Comparable<T>> T maxOf(T a, T b, T c) {
        T max = a;
        if (b.compareTo(max) > 0) {
            max = b;
        }
        if (c.compareTo(max) > 0) {
            max = c;
        }
        return max;
    }

    /**
     * 测试基本类型泛型在数组操作中的性能优势(PREVIEW)
     * JDK 26 PREVIEW 特性，需要 --enable-preview
     * 使用基本类型泛型可以避免大量装箱/拆箱操作
     */
    @Test
    public void testPrimitiveGenericPerformance_Preview() {
        // JDK 26 PREVIEW 特性，需要 --enable-preview
        int size = 10000;

        // 使用基本类型泛型 List<int>
        java.util.List<int> primitiveList = new java.util.ArrayList<>(size);
        long start1 = System.nanoTime();
        for (int i = 0; i < size; i++) {
            primitiveList.add(i);
        }
        int sum1 = 0;
        for (int i = 0; i < size; i++) {
            sum1 += primitiveList.get(i);
        }
        long end1 = System.nanoTime();
        System.out.println("List<int> 耗时: " + (end1 - start1) / 1_000_000.0 + " ms, 和: " + sum1);

        // 使用包装类型 List<Integer>
        java.util.List<Integer> boxedList = new java.util.ArrayList<>(size);
        long start2 = System.nanoTime();
        for (int i = 0; i < size; i++) {
            boxedList.add(i);
        }
        int sum2 = 0;
        for (int i = 0; i < size; i++) {
            sum2 += boxedList.get(i);
        }
        long end2 = System.nanoTime();
        System.out.println("List<Integer> 耗时: " + (end2 - start2) / 1_000_000.0 + " ms, 和: " + sum2);
    }
}