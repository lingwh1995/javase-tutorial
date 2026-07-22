package org.bluebridge.section_06_cas_operate;

import org.bluebridge.util.UnsafeAccessor;
import sun.misc.Unsafe;

/**
 * 学生实体类
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class Student {

    volatile int age;

    public void increment(int x) {
        while (true) {
            try {
                Unsafe unsafe = UnsafeAccessor.getUnsafe();
                long fieldOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("age"));
                if (unsafe.compareAndSwapInt(this, fieldOffset, x - 1, x)) {
                    break;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
