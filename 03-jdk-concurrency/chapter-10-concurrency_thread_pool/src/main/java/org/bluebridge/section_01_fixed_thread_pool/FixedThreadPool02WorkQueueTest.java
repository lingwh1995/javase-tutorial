package org.bluebridge.section_01_fixed_thread_pool;

import java.util.concurrent.*;

/**
 * LinkedBlockingQueue工作队列测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FixedThreadPool02WorkQueueTest {

    public static void main(String[] args) {
        // 创建一个容量为10的LinkedBlockingQueue
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        // 创建一个生产者线程，用于往队列中添加元素
        Thread producer = new Thread(() -> {
            try {
                // 生产20个元素
                for (int i = 0; i < 20; i++) {
                    queue.put("元素" + i); // 往队列中添加元素，如果队列已满则一直阻塞等待
                    System.out.println("生产元素" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 创建一个消费者线程，用于从队列中取出元素
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    String element = queue.take(); // 从队列中取出元素，如果队列为空则一直阻塞等待
                    System.out.println("消费元素：" + element);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动生产者线程和消费者线程
        producer.start();
        consumer.start();
    }
}
