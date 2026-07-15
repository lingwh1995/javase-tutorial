package org.bluebridge.jmm_01_volatile;

import java.util.concurrent.TimeUnit;

/**
 * 没有解决共享变量可见性问题
 *
 * main 线程对run变量的修改对于t线程不可见，导致了t线程无法停止
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class JMMSharedVariableTest {

    private static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (run) {

            }
        }, "t");
        t.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        // 线程t不会如预想的停下来
        run = false;
    }
}
