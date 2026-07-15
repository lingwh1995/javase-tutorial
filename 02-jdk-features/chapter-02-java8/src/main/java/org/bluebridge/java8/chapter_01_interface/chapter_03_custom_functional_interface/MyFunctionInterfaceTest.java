package org.bluebridge.java8.chapter_01_interface.chapter_03_custom_functional_interface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义函数式接口测试
 *
 * @author lingwh
 * @date 2025/12/2 16:03
 */
public class MyFunctionInterfaceTest {

    @Test
    public void testMyFunctionInterface() {
        MyFunctionInterface_1 myFunctionInterface1 = a -> (a - 5) >= 0;
        System.out.println("myFunctionInterface1.op() = " + myFunctionInterface1.op(10));

        MyFunctionInterface_2 myFunctionInterface2 = (a, b) -> a + b;
        System.out.println("myFunctionInterface2.op() = " + myFunctionInterface2.op(1, 2));

        MyFunctionInterface_3 myFunctionInterface3 = (a, b, c) -> a + b + c;
        System.out.println("myFunctionInterface3.op() = " + myFunctionInterface3.op(1, 2, 3));

        MyFunctionInterface_4 myFunctionInterface4 = () -> new Student("张三", 20);
        System.out.println("myFunctionInterface4.op() = " + myFunctionInterface4.op());

        MyFunctionInterface_5 myFunctionInterface5 = () -> {
            List<Student> students = new ArrayList<>();
            students.add(new Student("李四", 25));
            return students;
        };
        System.out.println("myFunctionInterface5.op() = " + myFunctionInterface5.op());

        MyFunctionInterface_6<String> myFunctionInterface6_1 = () -> new String("hello lambda~");
        System.out.println("myFunctionInterface6_1.op() = " + myFunctionInterface6_1.op());

        MyFunctionInterface_6<Student> myFunctionInterface6_2 = () -> new Student("王五", 26);
        System.out.println("myFunctionInterface6_2.op() = " + myFunctionInterface6_2.op());

        MyFunctionInterface_7<String, Integer> myFunctionInterface7_1 = s -> Integer.parseInt(s);
        System.out.println("myFunctionInterface7_1.op() = " + myFunctionInterface7_1.op("100"));

        Student student = new Student("张三", 23);

        MyFunctionInterface_7<Student, String> myFunctionInterface7_2 = stu -> stu.getName();
        System.out.println("myFunctionInterface7_2.op() = " + myFunctionInterface7_2.op(student));

        MyFunctionInterface_7<Student, Integer> myFunctionInterface7_3 = stu -> stu.getAge();
        System.out.println("myFunctionInterface7_3.op() = " + myFunctionInterface7_3.op(student));
    }
}
