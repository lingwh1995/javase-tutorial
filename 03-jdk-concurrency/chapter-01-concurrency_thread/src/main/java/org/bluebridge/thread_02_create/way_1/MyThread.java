package org.bluebridge.thread_02_create.way_1;

/**
 * @author lingwh
 * @desc 继承Thread类的自定义线程
 * @date 2026/7/9 00:00
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
    }
}
