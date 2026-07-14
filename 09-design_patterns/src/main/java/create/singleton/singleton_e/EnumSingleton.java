package create.singleton.singleton_e;

import java.lang.reflect.InvocationTargetException;

/**
 * 枚举形式的单例
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class EnumSingleton {

    public enum Singleton {
        INSTANCE;

        public void doString() {
            System.out.println("do something......");
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance1 == instance2);
        instance1.doString();
        instance2.doString();
    }
}
