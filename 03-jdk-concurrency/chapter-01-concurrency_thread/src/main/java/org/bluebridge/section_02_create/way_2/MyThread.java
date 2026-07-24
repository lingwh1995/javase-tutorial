package org.bluebridge.section_02_create.way_2;

/**
 * 实现 Runnable 接口的自定义线程
 *
 * @author lingwh
 * @date 2026/4/21 10:00
 */
public class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
    }
}
