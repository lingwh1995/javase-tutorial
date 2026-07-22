package org.bluebridge.section_24_semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 控制并发执行的任务数量
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FileProcessorTest {

    private static final int MAX_CONCURRENT_TASKS = 3;
    private static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_TASKS);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    semaphore.acquire();
                    processFile();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }

        executor.shutdown();
    }

    private static void processFile() {
        System.out.println(Thread.currentThread().getName() + " start processing file......");
        try {
            Thread.sleep(3000); // 模拟文件处理
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " done processing file......");
    }
}
