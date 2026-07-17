package org.bluebridge.lock_12_synchronized_revoke_biasedlock;

import java.util.Vector;
import org.openjdk.jol.info.ClassLayout;

/**
 * 批量重偏向锁测试
 *
 * 1. 测试偏向锁准备工作
 *    - JDK版本设置为1.8
 *    - VM配置参数设置为 -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 *
 * 2. 撤销偏向锁方式一：批量重偏向
 *    如果对象虽然被多个线程访问，但没有竞争，这时偏向了线程 T1 的对象仍有机会重新偏向 T2，重偏向会重置对象的Thread ID当
 *    撤销偏向锁阈值超过20次后，jvm会这样觉得，我是不是偏向错了呢，于是会在给这些对象加锁时重新偏向至加锁线程
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class Sychronized_RevokeBiasedLockTest04 {

    public static void main(String[] args) {
        Vector<Object> list = new Vector<>();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Object o = new Object();
                list.add(o);
                synchronized (o) {
                    System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
                }
            }
            synchronized (list) {
                list.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (list) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("--------------------");
            for (int i = 0; i < 30; i++) {
                Object o = list.get(i);
                System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
                synchronized (o) {
                    System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
                }
                System.out.println(i + "\t" + ClassLayout.parseInstance(o).toPrintableSimple());
            }
        }, "t2").start();
    }
}
