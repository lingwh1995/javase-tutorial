package org.bluebridge.thread.section_03_thread_designpattern.threadlocal_storage.threadlocal_storage_a;

import java.util.stream.IntStream;

/**
 * ThreadLocal 存储模式客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Client {

    public static void main(String[] args) {
        IntStream.range(1, 5)
                .forEach(
                        i -> {
                            // 每一个线程都有一个自己的上下文
                            new Thread(new ExecutionTask()).start();
                        });
    }
}
