package org.bluebridge.section_01_start;

import java.util.Arrays;
import java.util.concurrent.FutureTask;

/**
 * 单线程和多线程运行效率对比
 *
 * @author lingwh
 * @date 2026/4/21 09:00
 */
public class SingleThreadAndMultiThreadTest {

    public static void main(String[] args) throws Exception {
        // 测试多线程计算
        testMultiThread();

        // 测试单线程计算
        testSingleThread();
    }

    static int[] ARRAY = new int[1000_000_000];

    static {
        Arrays.fill(ARRAY, 10);
    }

    /**
     * 多线程计算
     *
     * @throws Exception
     */
    public static void testMultiThread() throws Exception {
        long start = System.currentTimeMillis();
        int[] array = ARRAY;
        FutureTask<Integer> t1 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 250_000_000; i++) {
                sum += array[0 + i];
            }
            return sum;
        });
        FutureTask<Integer> t2 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 250_000_000; i++) {
                sum += array[250_000_000 + i];
            }
            return sum;
        });
        FutureTask<Integer> t3 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 250_000_000; i++) {
                sum += array[500_000_000 + i];
            }
            return sum;
        });
        FutureTask<Integer> t4 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 250_000_000; i++) {
                sum += array[750_000_000 + i];
            }
            return sum;
        });
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();

        int total = t1.get() + t2.get() + t3.get() + t4.get();
        System.out.println("多线程 - total = " + total);
        long end = System.currentTimeMillis();
        System.out.println("多线程 - Total time (ms): " + (end - start));
    }

    /**
     * 单线程计算
     *
     * @throws Exception
     */
    public static void testSingleThread() throws Exception {
        long start = System.currentTimeMillis();
        int[] array = ARRAY;
        FutureTask<Integer> t1 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 1000_000_000; i++) {
                sum += array[0 + i];
            }
            return sum;
        });
        new Thread(t1).start();

        int total = t1.get();
        System.out.println("单线程 - total = " + total);
        long end = System.currentTimeMillis();
        System.out.println("单线程 - Total time (ms): " + (end - start));
    }
}
