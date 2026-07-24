package org.bluebridge.section_03_list;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Vector 是 ArrayList 的线程安全版本，Vector 的底层实现是数组，所以 Vector 的增删改查性能比 ArrayList 差
 *
 * @author lingwh
 * @date 2019/7/9 10:30
 */
public class VectorTest {

    @Test
    public void testVector() {
        Vector<Integer> nums = new Vector<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        System.out.println("nums = " + nums);
        System.out.println("-----------------------------------");

        Vector<String> vector = new Vector<>();
        vector.add("a");
        vector.add("b");
        vector.add("c");
        System.out.println(vector);
        System.out.println("-----------------------------------");
        System.out.println(vector.elementAt(1));
        System.out.println("-----------------------------------");
        Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }
    }
}
