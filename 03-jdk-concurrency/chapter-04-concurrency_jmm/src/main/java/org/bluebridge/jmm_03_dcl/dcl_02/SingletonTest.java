package org.bluebridge.jmm_03_dcl.dcl_02;

/**
 * @author lingwh
 * @desc 双重检查锁单例测试
 * @date 2026/7/9 00:00
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
