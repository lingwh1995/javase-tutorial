package org.design_pattern.flyweight.flyweight_a;

/**
 * @author lingwh
 * @desc Integer 享元模式测试
 * @date 2026/7/9 00:00
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
