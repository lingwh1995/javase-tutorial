package org.bluebridge.section_17_communication.wait_for_other_worker;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示 volatile 机制的使用场景：主线程等待多个工作线程完成
 *
 * @author lingwh
 * @date 2025/10/28 9:34
 */
@Slf4j
public class VolatileTest {

    // 使用volatile保证可见性
    private static volatile int completedWorkers = 0;
    // 总工作线程数量
    private static final int TOTAL_WORKERS = 3;

    public static void main(String[] args) throws InterruptedException {
        // 启动 3 个工作线程
        new Thread(new Worker("工作线程1 => 启动服务A")).start();
        new Thread(new Worker("工作线程2 => 启动服务B")).start();
        new Thread(new Worker("工作线程3 => 启动服务C")).start();

        log.info("主线程等待所有工作线程完成......");

        // 主线程轮询等待所有工作完成
        while (completedWorkers < TOTAL_WORKERS) {
            // 短暂休眠避免过度占用CPU
            Thread.sleep(100);
        }

        log.info("所有工作已完成，主线程继续执行......");
    }

    static class Worker implements Runnable {
        private final String name;

        public Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                log.info("{} 开始工作......", name);
                // 模拟工作耗时
                Thread.sleep((long) (Math.random() * 3000));
                log.info("{} 工作完成......", name);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // 增加完成的工作线程计数
            completedWorkers++;
        }
    }
}
