package org.bluebridge.thread_04_sleep;

import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc sleep() 应用 防止单核单线程时发生CPU 100% 被占用情况
 * @date 2026/7/9 00:00
 */
public class SleepCpuTest {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    //为了防止CPU被100%占用，没执行一次while(true)循环，睡眠50ms
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("...........................");
            }
        }).start();
    }
}
