package org.bluebridge.thread.section_03_thread_designpattern.thread_pre_message.thread_pre_message_a;

/**
 * Thread-Per-Message 模式消息
 *
 * @author lingwh
 * @date 2026/4/23 16:29
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
