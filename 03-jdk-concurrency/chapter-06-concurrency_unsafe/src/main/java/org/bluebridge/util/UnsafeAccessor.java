package org.bluebridge.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 获取Unsafe实例的静态方法
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class UnsafeAccessor {

    public static Unsafe getUnsafe() {
        try {
            // 获取Field对象
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            // Field unsafeField = Unsafe.class.getDeclaredFields()[0]; //也可以这样，作用相同
            // 设置Field可访问
            theUnsafe.setAccessible(true);
            // 因为 theUnsafe 属性是被 static修饰的，传入任何参数都是可以的，包括null
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
