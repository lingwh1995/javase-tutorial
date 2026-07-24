package org.bluebridge.section_02_create.way_1;

/**
 * 继承 Thread 类的自定义线程
 *
 * @author lingwh
 * @date 2026/4/21 09:30
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
    }
}
