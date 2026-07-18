package org.bluebridge.unsafe_04_object_operate;

import org.bluebridge.util.UnsafeAccessor;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe应用三 操作对象
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class UnsafeObjectOperateTest {

    @Test
    public void testUnsafeCreateObject() throws InstantiationException, IllegalAccessException {
        // 使用 new 关键字创建对象
        Student student = new Student();
        System.out.println("student.getName() = " + student.getName());

        // 使用 Student.class.newInstance() 方法创建对象
        student = Student.class.newInstance();
        System.out.println("student.getName() = " + student.getName());

        // 使用 Unsafe 创建对象
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        student = (Student) unsafe.allocateInstance(Student.class);
        System.out.println("student.getName() = " + student.getName());
    }

    /**
     * 测试获取指定字段在对象内存中的偏移地址，这个偏移地址仅在该Unsafe函数中访问指定字段时使用‌。
     */
    @Test
    public void testUnsafeObjectOperate() throws NoSuchFieldException {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        Field id = Student.class.getDeclaredField("id");
        Field name = Student.class.getDeclaredField("name");

        // 获得成员变量的偏移量
        long idOffset = unsafe.objectFieldOffset(id);
        System.out.println("idOffset = " + idOffset);
        long nameOffset = unsafe.objectFieldOffset(name);
        System.out.println("nameOffset = " + nameOffset);

        Student student = new Student();
        // 使用 cas 方法替换成员变量的值
        unsafe.compareAndSwapInt(student, idOffset, 0, 20); // 返回 true
        unsafe.compareAndSwapObject(student, nameOffset, null, "张三"); // 返回 true
        System.out.println(student);
    }
}
