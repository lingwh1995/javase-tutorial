package org.bluebridge.thread.section_03_thread_designpattern.thread_pre_message.thread_pre_message_a;

import java.util.stream.IntStream;

/**
 * Thread-Per-Message 模式客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class PreThreadClient {

    public static void main(String[] args) {
        MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(0, 10).forEach(i -> {
            handler.request(new Message(String.valueOf(i)));
        });
    }
}
