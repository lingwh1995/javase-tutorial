package org.bluebridge.thread.thread_designpattern.thread_pre_message.thread_pre_message_a;

import java.util.stream.IntStream;

/**
 * @author lingwh
 * @desc Thread-Per-Message 模式客户端
 * @date 2026/7/9 00:00
 */
public class PreThreadClient {
    public static void main(String[] args) {
        MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(0,10).forEach(i->{
            handler.request(new Message(String.valueOf(i)));
        });
    }
}
