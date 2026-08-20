package org.bluebridge.section_05_bitwise.unit_03_bitmap;

/**
 * 自定义位图测试
 *
 * @author lingwh
 * @date 2026/3/14 15:21
 */
public class MyBitMapTest {

    // 测试
    public static void main(String[] args) {
        MyBitMap bitMap = new MyBitMap(100);
        bitMap.set(35);
        System.out.println(bitMap.get(35)); // true
        System.out.println(bitMap.get(36)); // false
        bitMap.clear(35);
        System.out.println(bitMap.get(35)); // false
    }
}
