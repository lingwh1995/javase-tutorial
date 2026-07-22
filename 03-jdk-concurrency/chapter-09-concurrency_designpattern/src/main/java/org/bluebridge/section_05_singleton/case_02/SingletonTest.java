package org.bluebridge.section_05_singleton.case_02;

/**
 * 测试单例模式
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance1 == instance2);
    }
}
