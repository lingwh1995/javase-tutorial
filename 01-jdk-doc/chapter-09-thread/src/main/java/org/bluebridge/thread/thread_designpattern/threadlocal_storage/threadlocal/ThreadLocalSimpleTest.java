package org.bluebridge.thread.thread_designpattern.threadlocal_storage.threadlocal;

/**
 * @author lingwh
 * @desc ThreadLocal 简单测试
 * @date 2026/7/9 00:00
 */
public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String> (){
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
