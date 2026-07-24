package org.bluebridge.section_02_create.way_3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现 Callable 接口创建线程
 *
 * @author lingwh
 * @date 2026/4/21 10:45
 */
public class ThreadCreateTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 传统方式实现 Callable 接口创建线程
        // 创建线程对象
        Callable thread = new MyThread();
        // 使用FutureTask来包装线程对象
        FutureTask<Integer> futureTask1 = new FutureTask<>(thread);
        // FutureTask对象作为Thread对象的target创建新的线程
        // 不设置线程名称 new Thread() 中不用传递第二个参数
        Thread t1 = new Thread(futureTask1, "t1");
        // 线程进入到就绪状态
        t1.start();
        // 主线程阻塞，等待task执行完的结果
        final Integer r1 = futureTask1.get();
        System.out.println("Thread " + t1.getName() + " 执行结果是: " + r1);

        // 匿名内部类方式实现 Callable 接口创建线程
        // 使用FutureTask来包装线程对象
        FutureTask<Integer> futureTask2 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
                int i = 20;
                int j = 20;
                Thread.sleep(1000);
                return Integer.sum(i, j);
            }
        });
        // FutureTask对象作为Thread对象的target创建新的线程
        // 不设置线程名称 new Thread() 中不用传递第二个参数
        Thread t2 = new Thread(futureTask2, "t2");
        // 线程进入到就绪状态
        t2.start();
        // 主线程阻塞，等待task执行完的结果
        Integer r2 = futureTask2.get();
        System.out.println("Thread " + t2.getName() + " 执行结果是: " + r2);

        // lambda方式实现 Callable 接口创建线程
        // 使用FutureTask来包装线程对象
        FutureTask<Integer> futureTask3 = new FutureTask<>(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
            int i = 30;
            int j = 30;
            Thread.sleep(1000);
            return Integer.sum(i, j);
        });
        // FutureTask对象作为Thread对象的target创建新的线程
        // 不设置线程名称 new Thread() 中不用传递第二个参数
        Thread t3 = new Thread(futureTask3, "t3");
        // 线程进入到就绪状态
        t3.start();
        // 主线程阻塞，等待task执行完的结果
        Integer r3 = futureTask3.get();
        System.out.println("Thread " + t3.getName() + " 执行结果是: " + r3);

        System.out.println("Thread " + Thread.currentThread().getName() + " thread is running...");
    }
}
