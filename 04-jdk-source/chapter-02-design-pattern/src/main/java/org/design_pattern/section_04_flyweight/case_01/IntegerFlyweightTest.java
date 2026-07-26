package org.design_pattern.section_04_flyweight.case_01;

/**
 * Integer 享元模式测试
 *
 * @author lingwh
 * @date 2023/12/7 16:08
 */
public class IntegerFlyweightTest {

    public static void main(String[] args) {
        Integer x = Integer.valueOf(127);
        Integer y = new Integer(127);
        Integer z = Integer.valueOf(127);
        Integer w = new Integer(127);
        System.out.println(x.equals(y)); // true
        System.out.println(x == y); // fasle
        System.out.println(x == z); // true
        System.out.println(w == x); // false
        System.out.println(w == y); // false
    }
}
