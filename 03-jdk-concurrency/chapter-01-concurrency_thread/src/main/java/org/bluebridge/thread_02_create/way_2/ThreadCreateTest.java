package org.bluebridge.thread_02_create.way_2;

/**
 * @author lingwh
 * @desc 实现Runnable接口创建线程
 * @date 2026/7/9 00:00
 */
public class ThreadCreateTest {

    public static void main(String[] args) {
        // 传统方式实现Runnable接口创建线程
        Thread t1 = new Thread(new MyThread());
        // 不设置线程名称注掉下面这行
        t1.setName("t1");
        t1.start();


        // 匿名内部类方式实现Runnable接口创建线程
            // 不设置线程名称 new Thread() 中不用传递第二个参数
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
            }
        },"t2");
        t2.start();


        // lambda方式实现Runnable接口创建线程
            // 不设置线程名称 new Thread() 中不用传递第二个参数
        Thread t3 = new Thread(() -> System.out.println("Thread " + Thread.currentThread().getName() + " is running..."),"t3");
        t3.start();

        System.out.println("Thread " + Thread.currentThread().getName() + " thread is running...");
    }
}
