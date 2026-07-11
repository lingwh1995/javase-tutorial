package org.bluebridge.unsafe_08_class_operate;

import java.lang.reflect.Field;
import org.bluebridge.util.UnsafeAccessor;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc Unsafe应用七 操作class
 * @date 2026/7/9 00:00
 */
public class UnsafeClassOperateTest {

    @Test
    public void testUnsafeClassOperate() throws NoSuchFieldException {
        Student student = new Student();
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        Field sexField = Student.class.getDeclaredField("name");
        long fieldOffset = unsafe.staticFieldOffset(sexField);
        Object fieldBase = unsafe.staticFieldBase(sexField);
        Object object = unsafe.getObject(fieldBase, fieldOffset);
        System.out.println(object);
    }
}
