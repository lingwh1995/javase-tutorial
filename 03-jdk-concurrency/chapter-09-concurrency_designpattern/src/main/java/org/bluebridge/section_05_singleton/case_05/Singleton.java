package org.bluebridge.section_05_singleton.case_05;

/**
 * 实现5(内部类初始化)
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public final class Singleton {

    private Singleton() {
    }

    // 问题1：属于懒汉式还是饿汉式
    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
    }

    // 问题2：在创建时是否有并发问题
    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
