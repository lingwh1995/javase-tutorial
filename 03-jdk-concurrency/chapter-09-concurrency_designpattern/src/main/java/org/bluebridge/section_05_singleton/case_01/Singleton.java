package org.bluebridge.section_05_singleton.case_01;

import java.io.Serializable;

/**
 * 实现 1(饿汉式)
 *
 * 1. 问题 1：为什么加 final？
 *    防止被子类继承从而重写方法改写单例
 * 2. 问题 2：如果实现了序列化接口，还要做什么来防止反序列化破坏单例？
 *    重写 readResolve 方法
 *
 * @author lingwh
 * @date 2025/3/13 09:23
 */
public final class Singleton implements Serializable {

    // 问题 3：为什么设置为私有? 是否能防止反射创建新的实例?(防止外部调用构造方法创建多个实例；不能)
    private Singleton() {
    }

    // 问题 4：这样初始化是否能保证单例对象创建时的线程安全?(能，线程安全性由类加载器保障)
    private static final Singleton INSTANCE = new Singleton();

    // 问题 5：为什么提供静态方法而不是直接将 INSTANCE 设置为 public，说出你知道的理由(可以保证 instance
    // 的安全性，也能方便实现一些附加逻辑)
    public static Singleton getInstance() {
        return INSTANCE;
    }

    public Object readResolve() {
        return INSTANCE;
    }
}
