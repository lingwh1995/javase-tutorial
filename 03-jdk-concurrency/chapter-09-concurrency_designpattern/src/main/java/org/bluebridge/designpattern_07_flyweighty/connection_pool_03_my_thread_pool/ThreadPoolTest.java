package org.bluebridge.designpattern_07_flyweighty.connection_pool_03_my_thread_pool;

import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc 自定义线程池测试
 * @date 2026/7/9 00:00
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1);
        for (int i = 0; i < 3; i++) {
            int j = i;
            threadPool.execute(()->{
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
