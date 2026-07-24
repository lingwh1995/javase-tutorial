package org.bluebridge.section_12_synchronized_revoke_biasedlock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 撤销偏向锁测试(wait/notify)
 *
 * 1. 测试偏向锁准备工作
 *    - JDK版本设置为1.8
 *    - VM配置参数设置为 -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 *
 * 2. 撤销偏向锁方式一
 *    调用 wait/notify 涉及到了monitor对象，锁会升级到重量锁
 *
 * @author lingwh
 * @date 2026/7/9 10:45
 */
public class Sychronized_RevokeBiasedLockTest03 {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        new Thread(() -> {
            System.out.println(ClassLayout.parseInstance(o).toPrintableSimple());
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintableSimple());
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ClassLayout.parseInstance(o).toPrintableSimple());
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                System.out.println("notify......");
                o.notify();
            }
        }, "t2").start();
    }
}
