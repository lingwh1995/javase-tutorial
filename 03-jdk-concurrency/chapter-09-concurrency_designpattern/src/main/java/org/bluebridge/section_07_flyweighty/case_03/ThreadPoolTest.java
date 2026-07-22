package org.bluebridge.section_07_flyweighty.case_03;

import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1);
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
