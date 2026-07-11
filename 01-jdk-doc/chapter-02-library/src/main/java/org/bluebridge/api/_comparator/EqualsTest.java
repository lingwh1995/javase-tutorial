package org.bluebridge.api._comparator;

import org.junit.Test;

/**
 * @author lingwh
 * @desc equals方法测试
 * @date 2026/7/9 00:00
 */
public class EqualsTest {

    @Test
    public void testEquals() {
        Person p1 = new Person("张三", 10);
        Person p2 = new Person("张三", 10);
        System.out.printf("p1 == p2 : %s\n", p1.equals(p2));
    }
}
