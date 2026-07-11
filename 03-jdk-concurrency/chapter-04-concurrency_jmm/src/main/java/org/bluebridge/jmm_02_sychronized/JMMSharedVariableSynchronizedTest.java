package org.bluebridge.jmm_02_sychronized;

import java.util.concurrent.TimeUnit;

/**
 * 使用 synchronized 解决共享变量多线程可见性问题
 *
 * 1. synchronized 语句块既可以保证代码块的原子性，也同时保证代码块内变量的可见性。但缺点是 synchronized 是属于重量级操作，性能相对更低 。
 * 2. JMM关于synchronized的两条规定：
 * 　　- 线程解锁前，必须把共享变量的最新值刷新到主内存中
 * 　　- 线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新获取最新的值
 * 　　　（注意：加锁与解锁需要是同一把锁）
 *    通过以上两点，可以看到synchronized能够实现可见性。同时，由于synchronized具有同步锁，所以它也具有原子性
 *    如果在前面示例的死循环中加入 System.out.println() 会发现即使不加 volatile 修饰符，线程 t 也能正确看到 对 run 变量的修改了，想一想为什么？(println方法中有synchronized代码块保证了可见性)
 * 3. synchronized关键字不能阻止指令重排，但在一定程度上能保证有序性（如果共享变量没有逃逸出同步代码块的话）。因为在单线程的情况下指令重排不影响结果，相当于保障了有序性。
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class JMMSharedVariableSynchronizedTest {

    private static boolean run = true;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while(run){
                synchronized (lock) {
                    //System.out.println();
                }
            }
        },"t");
        t.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        synchronized (lock) {
            // 线程t不会如预想的停下来
            run = false;
        }
    }
}
