package org.bluebridge.section_02_deepclone;

import org.junit.Test;

import java.io.*;

/**
 * 深克隆测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class DeepCloneTest {

    /**
     * 测试使用序列化机制实现深克隆
     */
    @Test
    public void testDeepClone() throws IOException, ClassNotFoundException {
        Teacher teacher = new Teacher(1, 42, "张老师");
        Student student = new Student(1, "小明", 15, teacher);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        // 序列化
        oos.writeObject(student);
        // 关闭流
        oos.close();
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        // 反序列化
        Student studentCloneSerializable = (Student) ois.readObject();
        // 关闭流
        ois.close();
        // 原型对象:
        System.out.println("原Student：" + student);
        // 修改克隆对象中引用类型数据的属性值:
        Teacher teacher1 = new Teacher(1, 42, "张老师11111");
        studentCloneSerializable.setTeacher(teacher1);
        // 克隆对象:
        System.out.println("克隆的Student：" + studentCloneSerializable);
        System.out.println("修改克隆出来的副本后再打印Student：" + student);
    }
}
