package org.bluebridge.thread_pool_03_single_thread_executor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 单线程执行器其他API测试 SingleThreadExecutor
 *
 * 1. 希望多个任务排队执行。线程数固定为 1，任务数多于 1 时，会放入无界队列排队。任务执行完毕，这唯一的线程 也不会被释放。
 * 2. 使用的工作队列是LinkedBlockingQueue
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class SingleThreadExecutor03APITest {
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
        ExecutorService executor = Executors.newSingleThreadExecutor();
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
        ExecutorService executor = Executors.newSingleThreadExecutor();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss");
        List<Future<String>> futures = executor.invokeAll(Arrays.asList(
                () -> {
                    System.out.println(dtf.format(LocalDateTime.now()) + " " + Thread.currentThread().getName() + " begin......");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    System.out.println(dtf.format(LocalDateTime.now()) + " " + Thread.currentThread().getName() + " begin......");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    System.out.println(dtf.format(LocalDateTime.now()) + " " + Thread.currentThread().getName() + " begin......");
                    Thread.sleep(2000);
                    return "3";
                }
        ));

        futures.forEach( f ->  {
            try {
                System.out.println(dtf.format(LocalDateTime.now()) + " " + Thread.currentThread().getName() + " " + f.get());
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
        ExecutorService executor = Executors.newSingleThreadExecutor();
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
                }
        ));

        System.out.println("result = " + result);

        // 关闭线程池（必须显式关闭）
        executor.shutdown();
    }
}
