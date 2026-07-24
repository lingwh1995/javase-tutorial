package org.bluebridge.section_02_cached_thread_pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 可缓存的线程池 CahcedThreadPool
 *
 * 1. 核心线程数是 0， 最大线程数是 Integer.MAX_VALUE，救急线程的空闲生存时间是 60s，
 * 2. 意味着全部都是救急线程（60s 后可以回收）
 * 3. 救急线程可以无限创建
 * 4. 队列采用了 SynchronousQueue 实现特点是，它没有容量，没有线程来取是放不进去的（一手交钱、一手交货）
 *
 * @author lingwh
 * @date 2026/4/21 10:00
 */
public class CachedThreadPool02WorkQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.printf("putting... %d\n", 1);
                queue.put(1);
                System.out.printf("taked... %d\n", 1);

                System.out.printf("putting... %d\n", 2);
                queue.put(2);
                System.out.printf("taked... %d\n", 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        TimeUnit.MILLISECONDS.sleep(2000);

        new Thread(() -> {
            try {
                System.out.printf("taking... %d\n", 1);
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

        TimeUnit.MILLISECONDS.sleep(2000);

        new Thread(() -> {
            try {
                System.out.printf("taking... %d\n", 2);
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3").start();
    }
}
