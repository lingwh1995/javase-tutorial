package org.bluebridge.section_03_dcl.dcl_02;

/**
 * 双重检查锁单例测试
 *
 * @author lingwh
 * @date 2026/4/21 17:30
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
