package org.bluebridge.thread_16_jconsole;

import java.util.concurrent.TimeUnit;

/**
 * 打开cmd -> 输入jconsole -> 连接到JconsoleTest -> 点击线程标签
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class JconsoleTest {

    public static void main(String[] args) {
        new Thread(() -> read(), "READ-THREAD").start();
        new Thread(() -> write(), "WRITE-THREAD").start();
    }

    /**
     * 读文件
     */
    public static void read() {
        System.out.println("READ FROM FILE......");
        try {
            TimeUnit.MILLISECONDS.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写文件
     */
    public static void write() {
        System.out.println("WRITE TO DB......");
        try {
            TimeUnit.MILLISECONDS.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
