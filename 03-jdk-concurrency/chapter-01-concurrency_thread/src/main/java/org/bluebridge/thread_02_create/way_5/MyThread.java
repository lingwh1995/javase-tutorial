package org.bluebridge.thread_02_create.way_5;

/**
 * @author lingwh
 * @desc 实现Runnable接口的自定义线程
 * @date 2026/7/9 00:00
 */
public class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
    }
}
