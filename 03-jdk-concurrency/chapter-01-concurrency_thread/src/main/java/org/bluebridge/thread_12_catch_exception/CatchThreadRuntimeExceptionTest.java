package org.bluebridge.thread_12_catch_exception;

/**
 * @author lingwh
 * @desc 捕获线程运行时异常
 * @date 2026/7/9 00:00
 */
public class CatchThreadRuntimeExceptionTest {

    /**
     * 捕获线程运行时异常有两种方式: 1.使用 try...catch... 捕获 2.使用 setUncaughtExceptionHandler()
     * 捕获，因为线程类中的异常不能直接向上抛出，所以要使用此种方式捕获
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            int i = 1 / 0;
        });

        t.setUncaughtExceptionHandler((thread,e) -> {
            System.out.println(thread);
            System.out.println(e);
        });

        t.start();
    }
}
