package org.bluebridge.thread_17_communication.wait_for_compare_input;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 演示CountDownLatch的使用场景：输入内容，比较输入内容是否为 hello
 *
 * @author lingwh
 * @date 2025/10/28 10:07
 */
@Slf4j
public class CountDownLatchTest {

    // 输入内容
    private static final AtomicReference<String> INPUT = new AtomicReference<>(null);
    // 比较结果
    private static final AtomicReference<String> RESULT = new AtomicReference<>(null);
    // 用于输入比较的闭锁
    private static final CountDownLatch WAIT_LATCH = new CountDownLatch(1);

    public static void main(String[] args) {
        // 输入数字线程
        new Thread(
                () -> {
                    log.info("请输入内容:");
                    Scanner scanner = new Scanner(System.in);
                    INPUT.set(scanner.nextLine());
                    try {
                        log.info("等待比较用户输入内容中......");
                        WAIT_LATCH.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("比较结果: {}", RESULT.get());
                },
                "thread-input")
        .start();

        // 比较输入内容线程
        new Thread(
                () -> {
                    if ("hello".equals(INPUT.get())) {
                        RESULT.set("输入的内容是 hello");
                    } else {
                        RESULT.set("输入的内容不是 hello");
                    }
                    // 计数器减一
                    WAIT_LATCH.countDown();
                },
                "thread-compare")
        .start();
    }
}
