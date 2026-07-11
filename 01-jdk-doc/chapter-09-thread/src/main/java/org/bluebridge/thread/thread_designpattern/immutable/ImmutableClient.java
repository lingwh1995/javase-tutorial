package org.bluebridge.thread.thread_designpattern.immutable;

import java.util.stream.IntStream;

/**
 * @author lingwh
 * @desc 不可变对象客户端
 * @date 2026/7/9 00:00
 */
public class ImmutableClient {
    public static void main(String[] args) {
        Person person = new Person("Alex", "ShanXi");

        IntStream.range(0,5).forEach(i -> {
            new UsePersonThread(person).start();
        });
    }
}
