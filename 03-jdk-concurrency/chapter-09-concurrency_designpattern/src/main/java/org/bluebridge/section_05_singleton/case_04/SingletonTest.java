package org.bluebridge.section_05_singleton.case_04;

/**
 * 测试单例模式
 *
 * @author lingwh
 * @date 2025/3/9 16:17
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
