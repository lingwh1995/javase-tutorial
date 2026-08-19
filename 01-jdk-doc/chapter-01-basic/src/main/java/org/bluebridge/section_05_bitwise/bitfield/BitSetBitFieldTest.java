package org.bluebridge.section_05_bitwise.bitfield;

/**
 * 测试 BitSet 模拟 C 位域(标志位)
 *
 * @author lingwh
 * @date 2026/8/19 13:43
 */
public class BitSetBitFieldTest {

    public static void main(String[] args) {
        BitSetBitField permissions = new BitSetBitField();

        // 设置权限: 可读、可写、不可执行、不可删除
        permissions.setReadable(true);
        permissions.setWritable(true);
        permissions.setExecutable(false);
        permissions.setDeletable(true);

        // 打印内存布局
        permissions.showMemoryLayout();

        System.out.println("\n--- 使用 BitSet 模拟 C 位域(标志位) ---");
        System.out.println("readable    = " + permissions.isReadable());     // true
        System.out.println("writable    = " + permissions.isWritable());     // true
        System.out.println("executable  = " + permissions.isExecutable());   // false
        System.out.println("deletable   = " + permissions.isDeletable());    // false

        // 动态扩展: BitSet 不限于固定长度，可随时增加新的标志位
        System.out.println("\nBitSet 的长度可动态扩展, 适合标志数量不固定的场景");
    }
}
