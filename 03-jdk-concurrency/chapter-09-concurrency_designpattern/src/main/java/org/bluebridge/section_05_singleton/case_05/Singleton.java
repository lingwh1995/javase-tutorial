package org.bluebridge.section_05_singleton.case_05;

/**
 * 实现 5(内部类初始化)
 *
 * @author lingwh
 * @date 2025/3/13 14:41
 */
public final class Singleton {

    private Singleton() {
    }

    // 问题 1：属于懒汉式还是饿汉式
    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
    }

    // 问题 2：在创建时是否有并发问题
    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
