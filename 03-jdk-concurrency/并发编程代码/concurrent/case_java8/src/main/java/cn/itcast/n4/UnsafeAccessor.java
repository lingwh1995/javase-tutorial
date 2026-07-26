package cn.itcast.n4;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe 访问器
 *
 * @author lingwh
 * @date 2025/2/7 22:25
 */
public class UnsafeAccessor {

    private static final Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
