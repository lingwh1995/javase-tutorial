package org.bluebridge.thread_02_create.way_1;

/**
 * 继承Thread类的自定义线程
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
    }
}
