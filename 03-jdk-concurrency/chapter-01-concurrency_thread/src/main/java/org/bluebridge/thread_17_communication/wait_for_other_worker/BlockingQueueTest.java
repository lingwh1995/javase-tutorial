package org.bluebridge.thread_17_communication.wait_for_other_worker;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc 演示BlockingQueue机制的使用场景：主线程等待多个工作线程完成
 * @date 2025/10/28 9:34
 */
@Slf4j
public class BlockingQueueTest {

    // 总工作线程数量
    private static final int TOTAL_WORKERS = 3;
    // 创建容量为TOTAL_WORKERS的阻塞队列
    private static final BlockingQueue<String> COMPLETION_QUEUE = new ArrayBlockingQueue<>(TOTAL_WORKERS);

    public static void main(String[] args) throws InterruptedException {
        // 启动3个工作线程
        new Thread(new Worker("工作线程1 => 启动服务A")).start();
        new Thread(new Worker("工作线程2 => 启动服务B")).start();
        new Thread(new Worker("工作线程3 => 启动服务C")).start();

        log.info("主线程等待所有工作线程完成......");

        // 从队列中取出TOTAL_WORKERS个完成信号
        for (int i = 0; i < TOTAL_WORKERS; i++) {
            COMPLETION_QUEUE.take();
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

                // 向队列中放入完成信号
                COMPLETION_QUEUE.offer(name);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
