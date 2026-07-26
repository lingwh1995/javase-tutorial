package org.bluebridge.section_07_flyweighty.case_04;

import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池测试
 *
 * @author lingwh
 * @date 2025/3/20 17:19
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1, (queue, task) -> {
            // 死等
            // queue.put(task);
            // 带超时等待
            // queue.offer(task, 1500, TimeUnit.MILLISECONDS);
            // 让调用者放弃任务执行
            // System.out.println("放弃：" + task);
            // 让调用者抛出异常
            // throw new RuntimeException("任务执行失败" + task);
            // 让调用者自己执行任务
            task.run();
        });
        for (int i = 0; i < 3; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().toString() + "执行任务：" + j);
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
