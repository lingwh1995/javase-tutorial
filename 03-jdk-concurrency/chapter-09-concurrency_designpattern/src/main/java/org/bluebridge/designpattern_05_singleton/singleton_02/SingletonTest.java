package org.bluebridge.designpattern_05_singleton.singleton_02;

/**
 * @author lingwh
 * @desc 测试单例模式
 * @date 2026/7/9 00:00
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance1 == instance2);
    }
}
