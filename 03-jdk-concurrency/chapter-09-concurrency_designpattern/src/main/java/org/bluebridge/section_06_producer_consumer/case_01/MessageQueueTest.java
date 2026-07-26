package org.bluebridge.section_06_producer_consumer.case_01;

import java.util.concurrent.TimeUnit;

/**
 * 测试消息队列
 *
 * @author lingwh
 * @date 2025/2/28 15:36
 */
public class MessageQueueTest {

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "值" + id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Message message = queue.take();
                System.out.println("message = " + message);
            }
        }, "消费者").start();
    }
}
