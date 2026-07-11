package org.bluebridge.thread_17_communication.wait_for_other_worker;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc 演示ThreadLocal机制的使用场景：主线程等待多个工作线程完成
 * @date 2025/10/28 9:34
 */
@Slf4j
public class ThreadLocalTest {

    // 使用ThreadLocal存储每个线程的工作状态
    private static final ThreadLocal<Boolean> WORKER_COMPLETIONSTATUS = ThreadLocal.withInitial(() -> false);

    // 用于存储工作线程的引用
    private static final List<Thread> WORKER_THREADS = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // 创建并启动3个工作线程
        Thread worker1 = new Thread(new Worker("工作线程1 => 启动服务A"));
        Thread worker2 = new Thread(new Worker("工作线程2 => 启动服务B"));
        Thread worker3 = new Thread(new Worker("工作线程3 => 启动服务C"));

        // 保存线程引用
        WORKER_THREADS.add(worker1);
        WORKER_THREADS.add(worker2);
        WORKER_THREADS.add(worker3);

        // 启动所有工作线程
        worker1.start();
        worker2.start();
        worker3.start();

        log.info("主线程等待所有工作线程完成......");

        // 等待所有工作线程完成
        for (Thread workerThread : WORKER_THREADS) {
            workerThread.join();
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

                // 设置当前线程的工作完成状态
                WORKER_COMPLETIONSTATUS.set(true);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // 清理ThreadLocal变量
                WORKER_COMPLETIONSTATUS.remove();
            }
        }
    }
}
