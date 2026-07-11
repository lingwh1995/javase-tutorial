package org.bluebridge.thread_02_create.way_5;

import java.util.concurrent.CompletableFuture;

/**
 * @author lingwh
 * @desc 使用java8提供的CompletableFuture创建线程
 * @date 2026/7/9 00:00
 */
public class ThreadCreateTest {
    public static void main(String[] args) {
        // 传统方式实现 使用CompletableFuture创建线程
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(new MyThread());
        // 等待异步任务完成
        future1.join();

        // 匿名内部类方式实现 使用CompletableFuture创建线程
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // 异步执行的代码
                System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
            }
        });
        future2.join();

        // lambda方式实现Runnable接口创建线程
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            // 异步执行的代码
            System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
        });
        future3.join();
    }
}
