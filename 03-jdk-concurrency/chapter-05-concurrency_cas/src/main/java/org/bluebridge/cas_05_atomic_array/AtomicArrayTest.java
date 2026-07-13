package org.bluebridge.cas_05_atomic_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 原子数组测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AtomicArrayTest {

    public static void main(String[] args) {
        /**
         * 不安全的数组
         */
        demo(
            () -> new int[10],
            (array) -> array.length,
            (array, index) -> array[index]++,
            array -> System.out.println(Arrays.toString(array)));

        /**
         * 安全的数组
         */
        demo(
            () -> new AtomicIntegerArray(10),
            (array) -> array.length(),
            (array, index) -> array.getAndIncrement(index),
            array -> System.out.println(array));
    }

    /**
     * @param arraySupplier 提供数组、可以是线程不安全数组或线程安全数组 - 提供者
     * @param lengthFun 获取数组长度的方法
     * @param putConsumer 自增方法，回传 array, index
     * @param printConsumer
     * @param <T> 打印数组的方法 - 消费者
     */
    private static <T> void demo(
            Supplier<T> arraySupplier,
            Function<T, Integer> lengthFun,
            BiConsumer<T, Integer> putConsumer,
            Consumer<T> printConsumer) {
        List<Thread> ts = new ArrayList<>();
        T array = arraySupplier.get();
        int length = lengthFun.apply(array);
        for (int i = 0; i < length; i++) {
            // 每个线程对数组作 10000 次操作,每一次循环后，正常的情况下数组的元素的值会在原来基础上增加一千
            ts.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j % length);
                }
            }));
        }
        // 启动所有线程
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 等所有线程结束
        printConsumer.accept(array);
    }
}
