package org.bluebridge.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe 访问器 1
 *
 * @author lingwh
 * @date 2026/7/13 10:15
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
