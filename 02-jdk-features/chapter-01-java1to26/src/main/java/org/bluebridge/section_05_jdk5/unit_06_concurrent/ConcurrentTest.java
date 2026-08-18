package org.bluebridge.section_05_jdk5.unit_06_concurrent;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * JDK1.5 java.util.concurrent 并发包测试
 *
 * JDK1.5 首次引入了 java.util.concurrent 并发包, 提供了比 synchronized 更灵活、
 * 更强大的并发编程工具:
 * 1. ExecutorService: 线程池框架, 统一管理线程的创建、复用和销毁, 避免频繁创建线程的开销
 * 2. Callable/Future: Callable 任务可以有返回值(与 Runnable 相比), Future 用于获取异步执行结果
 * 3. CountDownLatch: 计数器闭锁, 让一个或多个线程等待其他线程完成操作后再继续执行
 * 4. Semaphore: 信号量, 控制同时访问某个资源的线程数量
 * 5. ConcurrentHashMap: 线程安全的 HashMap, 读操作不加锁, 并发性能远优于 Hashtable
 *
 * @author lingwh
 * @date 2026/08/05 18:27
 */
public class ConcurrentTest {

    /**
     * 测试 ExecutorService 线程池: 使用固定大小线程池执行任务
     */
    @Test
    public void testExecutorService() {
        // 创建固定大小为 3 的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 提交 5 个任务
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 正在执行任务 " + taskId);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 关闭线程池: 不再接受新任务, 等待已有任务执行完毕
        executor.shutdown();
        try {
            // 等待线程池中的所有任务执行完毕(最多等待 5 秒)
            boolean finished = executor.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("所有任务是否执行完毕: " + finished);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试 Callable 与 Future: Callable 任务可以返回结果, Future 获取异步结果
     */
    @Test
    public void testCallableAndFuture() {
        // 创建单线程线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // 提交 Callable 任务, 返回 Future 对象
        Future<Integer> future = executor.submit(() -> {
            // 模拟耗时计算
            Thread.sleep(500);
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        });
        // 主线程可以继续做其他事情, 不必等待任务完成
        System.out.println("Callable 任务已提交, 主线程继续执行...");
        try {
            // 获取异步执行结果(会阻塞等待任务完成)
            Integer result = future.get();
            System.out.println("Callable 返回结果(1~10 之和): " + result);
            // 判断任务是否已完成
            System.out.println("任务是否完成: " + future.isDone());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * 测试 CountDownLatch: 等待所有子线程执行完毕后再继续
     */
    @Test
    public void testCountDownLatch() throws InterruptedException {
        // 创建计数器为 3 的闭锁
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 启动 3 个子线程
        for (int i = 1; i <= 3; i++) {
            final int workerId = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 工人 " + workerId + " 开始工作");
                    Thread.sleep(300);
                    System.out.println(Thread.currentThread().getName() + " 工人 " + workerId + " 工作完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 计数器减 1
                    latch.countDown();
                }
            });
        }
        // 主线程等待计数器归零(所有子线程完成)后再继续执行
        latch.await();
        System.out.println("所有工人工作完成, 主线程继续执行");
        executor.shutdown();
    }

    /**
     * 测试 Semaphore 信号量: 控制同时访问资源的线程数量
     */
    @Test
    public void testSemaphore() {
        // 创建许可数为 2 的信号量: 同一时刻最多 2 个线程访问资源
        Semaphore semaphore = new Semaphore(2);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 启动 5 个线程竞争访问资源
        for (int i = 1; i <= 5; i++) {
            final int threadId = i;
            executor.execute(() -> {
                try {
                    // 获取许可(没有许可时会阻塞等待)
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 线程 " + threadId + " 获取许可, 访问资源");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放许可
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " 线程 " + threadId + " 释放许可");
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最终可用许可数: " + semaphore.availablePermits());
    }

    /**
     * 测试 ConcurrentHashMap: 线程安全的 HashMap, 多线程并发读写安全高效
     */
    @Test
    public void testConcurrentHashMap() {
        // 创建 ConcurrentHashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        // 多线程并发写入
        for (int i = 1; i <= 100; i++) {
            final int index = i;
            executor.execute(() -> map.put("key" + (index % 10), index));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印结果
        System.out.println("ConcurrentHashMap 大小: " + map.size());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
        // 并发场景下常用的原子操作: putIfAbsent 仅当键不存在时才放入, 返回旧值
        Integer oldValue = map.putIfAbsent("key1", 999);
        System.out.println("putIfAbsent(\"key1\", 999) 返回旧值: " + oldValue);
    }
}
