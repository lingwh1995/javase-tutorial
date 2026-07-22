package org.bluebridge.thread.section_03_thread_designpattern.producer_consumer.producer_consumer_a;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Client {

    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue(100);
        new Producer(messageQueue, 1).start();
        new Producer(messageQueue, 2).start();
        new Producer(messageQueue, 3).start();
        new Consumer(messageQueue, 1).start();
        new Consumer(messageQueue, 2).start();
    }
}
