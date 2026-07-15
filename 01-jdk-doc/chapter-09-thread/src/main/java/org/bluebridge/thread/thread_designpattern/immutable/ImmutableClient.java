package org.bluebridge.thread.thread_designpattern.immutable;

import java.util.stream.IntStream;

/**
 * 不可变对象客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ImmutableClient {

    public static void main(String[] args) {
        Person person = new Person("Alex", "ShanXi");

        IntStream.range(0, 5).forEach(i -> {
            new UsePersonThread(person).start();
        });
    }
}
