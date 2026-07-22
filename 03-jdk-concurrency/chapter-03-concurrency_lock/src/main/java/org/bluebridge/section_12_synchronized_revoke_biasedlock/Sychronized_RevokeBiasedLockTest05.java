package org.bluebridge.section_12_synchronized_revoke_biasedlock;

import org.openjdk.jol.info.ClassLayout;

import java.util.Vector;
import java.util.concurrent.locks.LockSupport;

/**
 * 批量撤销偏向锁测试
 *
 * 1. 测试偏向锁准备工作
 *    - JDK版本设置为1.8
 *    - VM配置参数设置为 -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 * 2. 撤销偏向锁方式一：批量撤销
 *    当撤销偏向锁阈值超过40次后，jvm会这样觉得，自己确实偏向错了，根本就不该偏向。于是整个类的所有
 *    对象都会变为不可偏向的，新建的对象也是不可偏向的
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class Sychronized_RevokeBiasedLockTest05 {

    static Thread t1, t2, t3;

    public static void main(String[] args) throws InterruptedException {
        Vector<Object> list = new Vector<>();
        int loopNumber = 39;
        t1 = new Thread(() -> {
            for (int i = 0; i < loopNumber; i++) {
                Object o = new Object();
                list.add(o);
                synchronized (o) {
                    System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
                }
            }
            LockSupport.unpark(t2);
        }, "t1");
        t1.start();

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("--------------------");
            for (int i = 0; i < loopNumber; i++) {
                Object o = list.get(i);
                System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
                synchronized (o) {
                    System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
                }
                System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
            }
            LockSupport.unpark(t3);
        }, "t2");
        t2.start();

        t3 = new Thread(() -> {
            LockSupport.park();
            System.out.println("--------------------");
            for (int i = 0; i < loopNumber; i++) {
                Object o = list.get(i);
                System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
                synchronized (o) {
                    System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
                }
                System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
            }
        }, "t3");
        t3.start();
        t3.join();

        System.out.println(ClassLayout.parseInstance(new Object()).toPrintableSimple());
    }
}
