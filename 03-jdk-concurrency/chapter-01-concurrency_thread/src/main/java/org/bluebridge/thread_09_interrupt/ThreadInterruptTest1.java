package org.bluebridge.thread_09_interrupt;

/**
 * 线程打断api测试
 *
 * 1. interrupt()      表示可以中断线程，实际上只是给线程设置一个中断标志，但是线程依旧会执行。
 * 2. interrupted()    Thread类的静态方法。检查当前线程的中断标志，返回一个boolean并清除中断状态，其连续两次调用的返回结果不一样，因为第二次调用的时候线程的中断状态已经被清除，会返回一个false。
 * 3. isInterrupted()  测试线程是否被中断，不会清除中断状态。
 *
 *  https://blog.csdn.net/shadow_zed/article/details/131169470
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class ThreadInterruptTest1 {
    public static void main(String[] args) throws InterruptedException {
        // 测试打断线程的api2
        // testThreadInterruptApi1();

        // 测试打断线程的api2
        // testThreadInterruptApi2();

        // 测试打断线程的api3
        testThreadInterruptApi3();
    }

    /**
     * 测试打断线程的api1
     */
    public static void testThreadInterruptApi1() {
        Thread t = new Thread(() -> {
            while (true) {
                //System.out.println("......");
                //System.out.println(Thread.currentThread().getName() + " is running...");
            }
        },"t1");
        t.start();
        t.interrupt();

        System.out.println("第一次调用 t.isInterrupted()：" + t.isInterrupted());
        System.out.println("第二次调用 t.isInterrupted()：" + t.isInterrupted());
        System.out.println("调用Thread.interrupted()方法，值为：" + Thread.interrupted());
        System.out.println("调用Thread.interrupted()方法，值为：" + Thread.interrupted());
        System.out.println("Thread " + t.getName() + " 存活状态： " + t.isAlive());
    }

    /**
     * 测试打断线程的api2
     */
    public static void testThreadInterruptApi2() {
        Thread.currentThread().interrupt();
        System.out.println("第一次调用isInterrupted()方法，值为：" + Thread.currentThread().isInterrupted());
        System.out.println("调用interrupted()方法，值为：" + Thread.currentThread().interrupted());
        System.out.println("调用interrupted()方法，值为：" + Thread.currentThread().interrupted());
        System.out.println("Thread " + Thread.currentThread().getName() + " 存活状态： " + Thread.currentThread().isAlive());
    }

    /**
     * 测试打断线程的api3
     */
    public static void testThreadInterruptApi3() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                System.out.println("Thread " + currentThread.getName() + " is running......");
                System.out.println("currentThread.isInterrupted()： " + currentThread.isInterrupted());
                if (currentThread.isInterrupted()) {//检查中断状态
                    System.out.println("第一个interrupted()： " + currentThread.interrupted());
                    System.out.println("第二个interrupted()： " + currentThread.interrupted());
                    break;
                }
            }
            //只有上面的while循环退出了，代码才可以执行到这里 -> 只有中断状态为true，才可以退出while循环
            System.out.println("检测到线程中断，线程执行结束......");
        },"t1");
        t.start();
        t.interrupt();
        Thread.sleep(2000);
        System.out.println("Thread " + t.getName() + " 存活状态： " + t.isAlive());
    }
}
