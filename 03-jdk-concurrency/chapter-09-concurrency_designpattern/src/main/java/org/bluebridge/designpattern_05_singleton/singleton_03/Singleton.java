package org.bluebridge.designpattern_05_singleton.singleton_03;

/**
 * 实现3(synchronized方法)
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public final class Singleton {

    private Singleton() {
    }

    private static Singleton INSTANCE = null;

    // 分析这里的线程安全, 并说明有什么缺点(没有线程安全问题，同步代码块粒度太大，性能差)
    public static synchronized Singleton getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        return new Singleton();
    }
}
