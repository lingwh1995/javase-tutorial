package org.bluebridge.section_07_jdk7.unit_05_forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * JDK 7 引入的 Fork/Join 框架测试
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class ForkJoinTest {

    /**
     * 测试 ForkJoinPool 执行 RecursiveTask 计算数组和
     */
    @Test
    public void testForkJoinSum() {
        // 创建一个大数组
        long[] numbers = LongStream.rangeClosed(1, 100_000_000).toArray();

        // 使用 Fork/Join 框架计算
        ForkJoinPool pool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        long forkJoinResult = pool.invoke(new SumTask(numbers, 0, numbers.length));
        long forkJoinTime = System.currentTimeMillis() - start;
        System.out.println("Fork/Join 计算结果: " + forkJoinResult + ", 耗时: " + forkJoinTime + " ms");

        pool.shutdown();
    }

    /**
     * 测试对比单线程执行
     */
    @Test
    public void testSingleThreadSum() {
        // 创建一个大数组
        long[] numbers = LongStream.rangeClosed(1, 100_000_000).toArray();

        // 单线程计算
        long start = System.currentTimeMillis();
        long singleResult = 0;
        for (long num : numbers) {
            singleResult += num;
        }
        long singleTime = System.currentTimeMillis() - start;
        System.out.println("单线程计算结果: " + singleResult + ", 耗时: " + singleTime + " ms");
    }

    /**
     * 测试 Fork/Join 框架的可配置并行度
     */
    @Test
    public void testForkJoinWithParallelism() {
        long[] numbers = LongStream.rangeClosed(1, 10_000_000).toArray();

        // 使用不同并行度的 ForkJoinPool
        int[] parallelismLevels = {2, 4, 8};
        for (int parallelism : parallelismLevels) {
            ForkJoinPool pool = new ForkJoinPool(parallelism);
            long start = System.currentTimeMillis();
            long result = pool.invoke(new SumTask(numbers, 0, numbers.length));
            long time = System.currentTimeMillis() - start;
            System.out.println("并行度 " + parallelism + " 计算结果: " + result + ", 耗时: " + time + " ms");
            pool.shutdown();
        }
    }

    /**
     * 测试 Fork/Join 分治算法 - 计算斐波那契数列
     */
    @Test
    public void testForkJoinFibonacci() {
        ForkJoinPool pool = new ForkJoinPool();
        int n = 40;

        long start = System.currentTimeMillis();
        long result = pool.invoke(new FibonacciTask(n));
        long time = System.currentTimeMillis() - start;
        System.out.println("Fork/Join 计算 Fibonacci(" + n + ") = " + result + ", 耗时: " + time + " ms");

        pool.shutdown();
    }

    /**
     * 测试对比单线程计算斐波那契数列
     */
    @Test
    public void testSingleThreadFibonacci() {
        int n = 40;

        long start = System.currentTimeMillis();
        long result = fibonacci(n);
        long time = System.currentTimeMillis() - start;
        System.out.println("单线程计算 Fibonacci(" + n + ") = " + result + ", 耗时: " + time + " ms");
    }

    /**
     * 单线程斐波那契数列计算
     */
    private long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 计算数组和的 RecursiveTask 实现
     * 当任务大小超过阈值时，将任务拆分为两个子任务并行执行
     */
    private static class SumTask extends RecursiveTask<Long> {
        // 阈值：每个任务处理的最大元素数量
        private static final long THRESHOLD = 1_000_000;

        private final long[] numbers;
        private final int start;
        private final int end;

        public SumTask(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;
            if (length <= THRESHOLD) {
                // 任务足够小，直接计算
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += numbers[i];
                }
                return sum;
            }

            // 任务过大，拆分为两个子任务
            int mid = start + length / 2;
            SumTask leftTask = new SumTask(numbers, start, mid);
            SumTask rightTask = new SumTask(numbers, mid, end);

            // 异步执行子任务
            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();

            // 合并结果
            return leftResult + rightResult;
        }
    }

    /**
     * 计算斐波那契数列的 RecursiveTask 实现
     */
    private static class FibonacciTask extends RecursiveTask<Long> {
        private final int n;

        public FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Long compute() {
            if (n <= 1) {
                return (long) n;
            }

            FibonacciTask f1 = new FibonacciTask(n - 1);
            f1.fork();
            FibonacciTask f2 = new FibonacciTask(n - 2);
            long result2 = f2.compute();
            long result1 = f1.join();

            return result1 + result2;
        }
    }
}