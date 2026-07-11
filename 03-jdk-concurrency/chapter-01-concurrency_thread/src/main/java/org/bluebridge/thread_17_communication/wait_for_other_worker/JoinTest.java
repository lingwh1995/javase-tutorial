package org.bluebridge.thread_17_communication.wait_for_other_worker;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc 演示join机制的使用场景：主线程等待多个工作线程完成
 * @date 2025/10/28 9:34
 */
@Slf4j
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        // 创建工作线程并保存引用
        Thread worker1 = new Thread(new Worker("工作线程1 => 启动服务A"));
        Thread worker2 = new Thread(new Worker("工作线程2 => 启动服务B"));
        Thread worker3 = new Thread(new Worker("工作线程3 => 启动服务C"));

        // 启动所有工作线程
        worker1.start();
        worker2.start();
        worker3.start();

        log.info("主线程等待所有工作线程完成......");

        // 使用join等待所有工作线程完成
        worker1.join();
        worker2.join();
        worker3.join();

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
        }
    }
}
