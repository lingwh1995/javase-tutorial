package org.bluebridge.cas_06_atomic_field_updater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 原子字段更新器测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Student> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "id");
        Student student = new Student();
        // 将 id 的值从 0 修改为 10
        atomicIntegerFieldUpdater.compareAndSet(student, 0, 10);
        System.out.println("student = " + student);
        // 将 id 的值从 10 修改为 20
        atomicIntegerFieldUpdater.compareAndSet(student, 10, 20);
        System.out.println("student = " + student);

        AtomicReferenceFieldUpdater<Student, String> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        // 将 name 的值从 null 修改为 张三
        atomicReferenceFieldUpdater.compareAndSet(student, null, "张三");
        System.out.println("student = " + student);
        // 将 name 的值从 张三 修改为 李四
        atomicReferenceFieldUpdater.compareAndSet(student, "张三", "李四");
        System.out.println("student = " + student);
    }
}
