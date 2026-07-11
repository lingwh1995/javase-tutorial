package org.bluebridge.thread.thread_designpattern.producer_consumer.producer_consumer_a;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lingwh
 * @desc 生产者
 * @date 2026/7/9 00:00
 */
public class Producer extends Thread {
    private final MessageQueue messageQueue;
    private static final Random random = new Random();
    private static final AtomicInteger counter = new AtomicInteger();

    public Producer(MessageQueue messageQueue, int seq) {
        super("PRODUCER" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message("Message-" + counter.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + ":put message " + message.getData());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
