package org.bluebridge.designpattern_05_singleton.singleton_03;

/**
 * @author lingwh
 * @desc 实现3(synchronized方法)
 * @date 2026/7/9 00:00
 */
public final class Singleton {
    private Singleton() {}

    private static Singleton INSTANCE = null;

    // 分析这里的线程安全, 并说明有什么缺点(没有线程安全问题，同步代码块粒度太大，性能差)
    public static synchronized Singleton getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        return new Singleton();
    }
}
