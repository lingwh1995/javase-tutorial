package org.bluebridge.designpattern_05_singleton.singleton_05;

import org.bluebridge.designpattern_05_singleton.singleton_04.Singleton;

/**
 * 测试单例模式
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
