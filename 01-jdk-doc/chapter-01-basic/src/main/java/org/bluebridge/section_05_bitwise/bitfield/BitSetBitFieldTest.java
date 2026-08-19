package org.bluebridge.section_05_bitwise.bitfield;

/**
 * 测试 BitSet 模拟 C 位域(标志位)
 *
 * @author lingwh
 * @date 2026/8/19 13:43
 */
public class BitSetBitFieldTest {

    public static void main(String[] args) {
        BitSetBitField bitField = new BitSetBitField();

        // 设置权限: 可读、可写、不可执行、不可删除
        bitField.setReadable(true);
        bitField.setWritable(true);
        bitField.setExecutable(false);
        bitField.setDeletable(false);

        System.out.println("--- 使用 BitSet 模拟 C 位域(标志位) ---");
        System.out.println("readable    = " + bitField.isReadable());     // true
        System.out.println("writable    = " + bitField.isWritable());     // true
        System.out.println("executable  = " + bitField.isExecutable());   // false
        System.out.println("deletable   = " + bitField.isDeletable());    // false

        // 动态扩展: BitSet 不限于固定长度, 可随时增加新的标志位
        System.out.println();
        System.out.println("BitSet 的长度可动态扩展, 适合标志数量不固定的场景");
    }
}
