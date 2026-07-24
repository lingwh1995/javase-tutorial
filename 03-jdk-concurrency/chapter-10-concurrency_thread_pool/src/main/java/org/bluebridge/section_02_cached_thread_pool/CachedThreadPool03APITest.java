package org.bluebridge.section_02_cached_thread_pool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 可缓存的线程池 CahcedThreadPool
 *
 * 1. 核心线程数是 0， 最大线程数是 Integer.MAX_VALUE，救急线程的空闲生存时间是 60s，
 * 2. 意味着全部都是救急线程（60s 后可以回收）
 * 3. 救急线程可以无限创建
 * 4. 队列采用了 SynchronousQueue 实现特点是，它没有容量，没有线程来取是放不进去的（一手交钱、一手交货）
 *
 * @author lingwh
 * @date 2026/4/21 10:15
 */
public class CachedThreadPool03APITest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // testSubmit();

        // testInvokeAll();

        testInvokeAny();
    }

    /**
     * 测试submit()
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testSubmit() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " running......");
            TimeUnit.MILLISECONDS.sleep(1000);
            return "ok";
        });

        System.out.println(future.get());

        // 关闭线程池（必须显式关闭）
        executor.shutdown();
    }

    /**
     * 测试invokeAll()
     *
     * @throws InterruptedException
     */
    private static void testInvokeAll() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss");
        List<Future<String>> futures = executor.invokeAll(Arrays.asList(
                () -> {
                    System.out.println(
                            dtf.format(LocalDateTime.now()) + " " + Thread.currentThread().getName() + " begin......");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    System.out.println(
                            dtf.format(LocalDateTime.now()) + " " + Thread.currentThread().getName() + " begin......");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    System.out.println(
                            dtf.format(LocalDateTime.now()) + " " + Thread.currentThread().getName() + " begin......");
                    Thread.sleep(2000);
                    return "3";
                }));

        futures.forEach(f -> {
            try {
                System.out.println(
                        dtf.format(LocalDateTime.now()) + " " + Thread.currentThread().getName() + " " + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 关闭线程池（必须显式关闭）
        executor.shutdown();
    }

    /**
     * 测试invokeAny()
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testInvokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        String result = executor.invokeAny(Arrays.asList(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " begin......1");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " end......1");
                    return "1";
                },
                () -> {
                    System.out.println(Thread.currentThread().getName() + " begin......2");
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " end......2");
                    return "2";
                },
                () -> {
                    System.out.println(Thread.currentThread().getName() + " begin......3");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " end......3");
                    return "3";
                }));

        System.out.println("result = " + result);

        // 关闭线程池（必须显式关闭）
        executor.shutdown();
    }
}
