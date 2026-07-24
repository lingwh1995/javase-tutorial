package org.bluebridge.section_02_create.way_5;

/**
 * 实现 Runnable 接口的自定义线程
 *
 * @author lingwh
 * @date 2026/4/21 11:30
 */
public class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
    }
}
