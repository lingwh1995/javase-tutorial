package create.singleton.singleton_f;

/**
 * @author lingwh
 * @desc 单例模式
 * @date 2026/7/9 00:00
 */
public class Singleton {
    /**
     * 注意下面三行代码的顺序决定了Singleton.x和Singleton.y的值
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
