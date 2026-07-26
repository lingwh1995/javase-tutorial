package org.bluebridge.section_05_singleton.case_04;

/**
 * 实现 4：DCL+volatile
 *
 * @author lingwh
 * @date 2025/3/10 09:37
 */
public final class Singleton {

    private Singleton() {
    }

    // 问题 1：解释为什么要加 volatile ?(防止 putstatic 和 invokespecial 重排导致的异常)
    private static volatile Singleton INSTANCE = null;

    // 问题 2：对比实现 3，说出这样做的意义 (缩小了锁的粒度，提高了性能)
    public static Singleton getInstance() {
        // 实例没创建，才会进入内部的 synchronized 代码块
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
