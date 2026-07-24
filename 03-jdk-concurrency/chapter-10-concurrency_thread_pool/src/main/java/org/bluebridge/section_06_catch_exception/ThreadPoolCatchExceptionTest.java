package org.bluebridge.section_06_catch_exception;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池异常处理测试
 *
 * @author lingwh
 * @date 2026/4/21 11:45
 */
public class ThreadPoolCatchExceptionTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // testTryCatchDealException();

        testFutureDealException();
    }

    /**
     * 使用try...catch...捕获异常
     */
    public static void testTryCatchDealException() {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.submit(() -> {
            try {
                System.out.println("task1......");
                int i = 1 / 0;
            } catch (Exception e) {
                System.out.println("error = " + e);
            }
        });

        // 关闭线程池
        pool.shutdown();
    }

    /**
     * 使用 Future 处理异常
     */
    public static void testFutureDealException() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> future = pool.submit(() -> {
            System.out.println("task1......");
            int i = 1 / 0;
            return true;
        });

        System.out.println("future" + future.get());

        // 关闭线程池
        pool.shutdown();
    }
}
