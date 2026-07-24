package org.bluebridge.section_03_dcl.dcl_02;

/**
 * 双重检查锁单例模式 (volatile) 修正
 *
 * 1. 使用 final 修饰类，可以防止因为继承而破坏单例模式
 *
 * @author lingwh
 * @date 2026/4/21 17:15
 */
public final class Singleton {

    private Singleton() {
    }

    private static volatile Singleton INSTANCE = null;

    public static Singleton getInstance() {
        // 实例没创建，才会进入内部的 synchronized代码块
        if (INSTANCE == null) {
            synchronized (Singleton.class) { // t2
                // 也许有其它线程已经创建实例，所以再判断一次
                if (INSTANCE == null) { // t1
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
