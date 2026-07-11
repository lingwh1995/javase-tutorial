package org.bluebridge.util;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc Unsafe访问器1
 * @date 2026/7/9 00:00
 */
public class UnsafeAccessor1 {
    static Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    static Unsafe getUnsafe() {
        return unsafe;
    }
}
