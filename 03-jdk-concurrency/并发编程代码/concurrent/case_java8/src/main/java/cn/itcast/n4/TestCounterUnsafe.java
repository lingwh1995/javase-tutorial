package cn.itcast.n4;

import lombok.extern.slf4j.Slf4j;

/**
 * 不安全计数器测试
 *
 * @author lingwh
 * @date 2025/2/7 16:05
 */
@Slf4j(topic = "c.TestCounterUnsafe")
public class TestCounterUnsafe {

    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}",counter);
    }
}
