package org.bluebridge.thread.section_03_thread_designpattern.threadlocal_storage.threadlocal;

/**
 * ThreadLocal 简单测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };

    public static void main(String[] args) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = threadLocal.get();
        System.out.println(result);
    }
}
