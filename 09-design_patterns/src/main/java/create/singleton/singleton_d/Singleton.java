package create.singleton.singleton_d;

/**
 * 双重检查锁单例模式
 *
 * volatile不能保证原子性，能保证内存可见性，多个线程看到的数据是同一份
 *
 * @author lingwh
 * @date 2019/8/5 18:29
 */
public class Singleton {
    /**
     * 对保存实例的变量添加volatile的修饰
     */
    private static volatile Singleton instance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        // 先检查实例是否存在，如果不存在才进入下面的同步块
        if (instance == null) {
            // 同步块，线程安全的创建实例
            synchronized (Singleton.class) {
                // 再次检查实例是否存在，如果不存在才真的创建实例
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        // return Singleton.instance;
        return instance;
    }
}
