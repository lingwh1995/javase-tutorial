package org.bluebridge.thread.section_03_thread_designpattern.producer_consumer.producer_consumer_a;

/**
 * 消息
 *
 * @author lingwh
 * @date 2026/4/23 16:29
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
