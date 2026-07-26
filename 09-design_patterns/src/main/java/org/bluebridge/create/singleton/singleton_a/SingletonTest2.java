package org.bluebridge.create.singleton.singleton_a;

/**
 * 饿汉式：静态代码块
 *
 * @author lingwh
 * @date 2019/3/23 22:10
 */
public class SingletonTest2 {

    public static void main(String[] args) {
        Singleton2 instance1 = Singleton2.getInstance();
        Singleton2 instance2 = Singleton2.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }
}

class Singleton2 {
    private Singleton2() {
    }

    /**
     * 饿汉式
     *
     * 1. 线程安全，效率高，但是不能延时加载
     * 2. 巧记： 恶汉式，非常饿，上来就吃了
     * 3. 效率高体现在不使用同步
     */
    private static Singleton2 singleton2 = null;

    static {
        singleton2 = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return singleton2;
    }
}
