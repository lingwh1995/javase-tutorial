package org.bluebridge.util;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc 获取Unsafe实例的静态方法
 * @date 2026/7/9 00:00
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
