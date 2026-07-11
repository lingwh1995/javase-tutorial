package org.bluebridge.thread.thread_designpattern.producer_consumer.producer_consumer_a;

import java.util.LinkedList;

/**
 * @author lingwh
 * @desc 消息队列
 * @date 2026/7/9 00:00
 */
public class MessageQueue {
    private final LinkedList<Message> queue;
    private static final int DEFAULT_MAX_LIMIT = 100;
    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public void put(final Message message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > limit) {
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getMaxLimit() {
        return this.limit;
    }

    public int getMessageSize() {
        synchronized (queue) {
            return this.queue.size();
        }
    }
}
