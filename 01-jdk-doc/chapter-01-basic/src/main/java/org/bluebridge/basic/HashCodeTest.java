package org.bluebridge.basic;

/**
 * hashCode 和
 *
 * @author lingwh
 * @date 2026/8/4 00:30
 */
public class HashCodeTest {

    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = new String("abc");

        // String重写了hashCode，根据字符内容计算
        System.out.println(s1.hashCode());   // 96354
        System.out.println(s2.hashCode());   // 96354

        // identityHashCode 不受重写影响，基于对象内存标识
        System.out.println(System.identityHashCode(s1)); // 地址衍生值A
        System.out.println(System.identityHashCode(s2)); // 地址衍生值B（和上面不一样）
    }
}
