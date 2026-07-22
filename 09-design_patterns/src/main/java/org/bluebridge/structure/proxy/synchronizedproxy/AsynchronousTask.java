package org.bluebridge.structure.proxy.synchronizedproxy;

/**
 * 异步任务
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AsynchronousTask implements Task {

    @Override
    public void task() {
        Thread thread1 = createThread1();
        Thread thread2 = createThread2();
        thread1.start();
        thread2.start();
    }

    /**
     * 创建第一个线程
     *
     * @return
     */
    @Override
    public Thread createThread1(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("异步任务一...");
            }
        };
        return new Thread(runnable);
    }

    /**
     * 创建第一个线程
     *
     * @return
     */
    @Override
    public Thread createThread2(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("异步任务二...");
            }
        };
        return new Thread(runnable);
    }
}
