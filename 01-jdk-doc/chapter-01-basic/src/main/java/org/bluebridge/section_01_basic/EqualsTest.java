package org.bluebridge.section_01_basic;

import org.bluebridge.section_04_comparator.Person;
import org.junit.Test;

/**
 * equals方法测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class EqualsTest {

    @Test
    public void testEquals() {
        Person p1 = new Person("张三", 10);
        Person p2 = new Person("张三", 10);
        System.out.printf("p1 == p2 : %s\n", p1.equals(p2));
    }
}
