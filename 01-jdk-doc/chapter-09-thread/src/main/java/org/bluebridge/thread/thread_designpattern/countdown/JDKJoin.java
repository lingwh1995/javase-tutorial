package org.bluebridge.thread.thread_designpattern.countdown;

import java.util.Random;

/**
 * @author lingwh
 * @desc 使用 JDK Join 实现倒计时
 * @date 2026/7/9 00:00
 */
public class JDKJoin {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        System.out.println("准备多线程处理任务......");
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is working......");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i));
            thread.start();
            thread.join();
        }
        System.out.println("多线程任务全部结束,准备第二阶段任务");
        System.out.println("FINISHED......");
    }
}
