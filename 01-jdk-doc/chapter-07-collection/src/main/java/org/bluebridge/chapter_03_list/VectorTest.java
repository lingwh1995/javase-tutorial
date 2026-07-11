package org.bluebridge.chapter_03_list;

import java.util.Enumeration;
import java.util.Vector;
import org.junit.Test;

/**
 * @author lingwh
 * @desc Vector是ArrayList的线程安全版本，Vector的底层实现是数组，所以Vector的增删改查性能比ArrayList差
 * @date 2026/7/9 00:00
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
