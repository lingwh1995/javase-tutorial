package org.bluebridge.section_03_balking.case_03;

/**
 * 测试单例模式
 *
 * @author lingwh
 * @date 2025/3/9 15:55
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
