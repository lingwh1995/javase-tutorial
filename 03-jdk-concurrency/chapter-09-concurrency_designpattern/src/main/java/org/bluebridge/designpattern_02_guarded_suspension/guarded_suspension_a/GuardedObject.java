package org.bluebridge.designpattern_02_guarded_suspension.guarded_suspension_a;

import org.openjdk.jol.info.ClassLayout;

/**
 * 关联对象-GuardedObject
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class GuardedObject {

    // 结果
    private Object response;

    private final Object lock = new Object();

    /**
     * 获取结果(没有结果将处于一直等待状态,等待结果的产生)
     *
     * @return
     */
    public Object get() {
        synchronized (lock) {
            System.out.println("Thread " + Thread.currentThread().getName() + " 锁状态 "
                    + ClassLayout.parseInstance(lock).toPrintableSimple());
            System.out.printf("进入此代码块说明当前线程%s获取到了锁......\n", Thread.currentThread().getName());
            // 没有结果则一直等待
            // 防止虚假唤醒
            while (response == null) {
                try {
                    // 代码如果走到了这里不会阻塞住的原因是 wait()等待时会释放锁对象，不会一直占据着锁对象不放
                    System.out.println("标识位置-1");
                    lock.wait();
                    System.out.println("标识位置-2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    /**
     * 产生结果
     *
     * @param response
     */
    public void complete(Object response) {
        synchronized (lock) {
            System.out.println("Thread " + Thread.currentThread().getName() + " 锁状态 "
                    + ClassLayout.parseInstance(lock).toPrintableSimple());
            System.out.printf("进入此代码块说明当前线程%s获取到了锁......\n", Thread.currentThread().getName());
            // 给成员变量赋值
            this.response = response;
            // 产生结果，通知等待线程
            System.out.println("标识位置-3");
            lock.notifyAll();
            System.out.println("标识位置-4");
        }
    }
}
