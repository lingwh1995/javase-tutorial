package org.bluebridge.thread.thread_designpattern.producer_consumer.producer_consumer_a;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2026/7/9 00:00
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
