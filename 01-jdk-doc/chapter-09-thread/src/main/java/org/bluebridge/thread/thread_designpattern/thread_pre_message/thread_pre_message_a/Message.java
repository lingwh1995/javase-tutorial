package org.bluebridge.thread.thread_designpattern.thread_pre_message.thread_pre_message_a;

/**
 * @author lingwh
 * @desc Thread-Per-Message 模式消息
 * @date 2026/7/9 00:00
 */
public class Message {
    private final String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
