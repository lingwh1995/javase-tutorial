package org.bluebridge.section_02_create.way_1;

/**
 * 继承 Thread 类创建线程
 *
 * @author lingwh
 * @date 2026/4/21 09:45
 */
public class ThreadCreateTest {

    public static void main(String[] args) {
        // 传统方式继承 Thread 类接口创建线程
        Thread t1 = new MyThread();
        // 不设置线程名称注掉下面这行
        t1.setName("t1");
        t1.start();

        // 匿名内部类方式继承 Thread 类接口创建线程
        // 不设置线程名称 new Thread() 中不用传递参数
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
            }
        };
        t2.start();

        // lambda 方式继承 Thread 类接口创建线程
        // 不设置线程名称 new Thread() 中不用传递第二个参数
        Thread t3 = new Thread(() -> System.out.println("Thread " + Thread.currentThread().getName() + " is running..."),"t3");
        t3.start();

        System.out.println("Thread " + Thread.currentThread().getName() + " thread is running...");
    }
}
