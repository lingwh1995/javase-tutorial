package org.bluebridge.thread_17_communication.wait_for_compare_input;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc 演示原子变量的使用场景：输入内容，比较输入内容是否为 hello
 * @date 2025/10/28 18:20
 */
@Slf4j
public class AtomicTest {

    // 输入内容
    private static final AtomicReference<String> INPUT = new AtomicReference<>(null);
    // 比较结果
    private static final AtomicReference<String> RESULT = new AtomicReference<>(null);
    // 比较准备就绪标志
    private static final AtomicBoolean COMPARISON_READY_FLAG = new AtomicBoolean(false);

    public static void main(String[] args) {
        // 输入数字线程
        new Thread(() -> {
            log.info("请输入内容:");
            Scanner scanner = new Scanner(System.in);
            INPUT.set(scanner.nextLine());
            log.info("等待比较用户输入内容中......");

            if(!COMPARISON_READY_FLAG.get()) {
                return;
            }

            log.info("比较结果: {}", RESULT.get());
        }, "thread-input").start();

        // 比较输入内容线程
        new Thread(() -> {
            if("hello".equals(INPUT.get())) {
                RESULT.set("输入的内容是 hello");
            } else {
                RESULT.set("输入的内容不是 hello");
            }
            COMPARISON_READY_FLAG.set(true);
        },"thread-compare").start();
    }
}
