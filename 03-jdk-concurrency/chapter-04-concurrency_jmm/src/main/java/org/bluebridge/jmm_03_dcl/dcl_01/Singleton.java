package org.bluebridge.jmm_03_dcl.dcl_01;

/**
 * 双重检查锁单例模式（dcl）实现单例模式的特点
 *
 * 1. 懒惰实例化
 * 2. 首次使用 getInstance() 才使用 synchronized 加锁，后续使用时无需加锁
 * 3. 有隐含的，但很关键的一点：第一个 if 使用了 INSTANCE 变量，是在同步块之外
 *
 * 注意
 * 1. 使用final修饰类，可以防止因为继承而破坏单例模式
 * 2. 在多线程环境下，上面的代码是有问题的
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public final class Singleton {
    private Singleton() {}

    private static Singleton INSTANCE = null;

    public static Singleton getInstance() {
        if (INSTANCE == null) { // t2
            // 首次访问会同步，而之后的使用没有 synchronized
            synchronized (Singleton.class) {
                if (INSTANCE == null) { // t1
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
