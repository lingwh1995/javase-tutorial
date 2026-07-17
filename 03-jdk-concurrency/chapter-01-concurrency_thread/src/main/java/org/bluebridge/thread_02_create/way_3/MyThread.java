package org.bluebridge.thread_02_create.way_3;

import java.util.concurrent.Callable;

/**
 * 实现Callable接口的自定义线程
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
        int i = 10;
        int j = 10;
        Thread.sleep(1000);
        return Integer.sum(i, j);
    }
}
