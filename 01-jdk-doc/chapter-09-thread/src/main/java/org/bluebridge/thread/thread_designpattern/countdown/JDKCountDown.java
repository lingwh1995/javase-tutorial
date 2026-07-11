package org.bluebridge.thread.thread_designpattern.countdown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author lingwh
 * @desc 使用 JDK CountDownLatch 实现倒计时
 * @date 2026/7/9 00:00
 */
public class JDKCountDown {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        System.out.println("准备多线程处理任务......");
        IntStream.rangeClosed(1,5).forEach(i->
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +" is working......");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            },String.valueOf(i)).start()
        );
        latch.await();
        System.out.println("多线程任务全部结束,准备第二阶段任务");
        System.out.println("FINISHED......");
    }
}
