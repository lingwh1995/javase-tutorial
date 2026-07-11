package org.bluebridge.thread.thread_designpattern.producer_consumer.producer_consumer_a;

/**
 * @author lingwh
 * @desc 消息
 * @date 2026/7/9 00:00
 */
public class Message {
    private String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
