package org.bluebridge.designpattern_05_singleton.singleton_05;

/**
 * @author lingwh
 * @desc 实现5(内部类初始化)
 * @date 2026/7/9 00:00
 */
public final class Singleton {
    private Singleton() {}

    // 问题1：属于懒汉式还是饿汉式
    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
    }

    // 问题2：在创建时是否有并发问题
    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
