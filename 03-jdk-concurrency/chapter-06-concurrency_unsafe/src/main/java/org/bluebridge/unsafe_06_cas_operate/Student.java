package org.bluebridge.unsafe_06_cas_operate;

import org.bluebridge.util.UnsafeAccessor;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc 学生实体类
 * @date 2026/7/9 00:00
 */
public class Student {
    volatile int age;

    public void increment(int x) {
        while (true) {
            try {
                Unsafe unsafe = UnsafeAccessor.getUnsafe();
                long fieldOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("age"));
                if (unsafe.compareAndSwapInt(this, fieldOffset, x - 1, x)) break;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
