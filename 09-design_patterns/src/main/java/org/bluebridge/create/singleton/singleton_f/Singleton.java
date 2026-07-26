package org.bluebridge.create.singleton.singleton_f;

/**
 * 单例模式
 *
 * @author lingwh
 * @date 2026/7/22 09:17
 */
public class Singleton {

    /**
     * 注意下面三行代码的顺序决定了 Singleton.x 和 Singleton.y 的值
     */
    private static Singleton instance = new Singleton();

    public static int x = 0;
    public static int y;

    private Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {
        return instance;
    }
}
