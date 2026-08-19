package org.bluebridge.section_05_bitwise.bitfield;

import java.util.BitSet;

/**
 * 使用 BitSet 模拟 C 语言位域(Bit Field)
 *
 * C 语言中位域常用于表示一组布尔标志:
 *   struct Permissions {
 *       unsigned int readable    : 1;   // 1位
 *       unsigned int writable    : 1;   // 1位
 *       unsigned int executable  : 1;   // 1位
 *       unsigned int deletable   : 1;   // 1位
 *   };
 *
 * Java 中 BitSet 本质是一个可变长位向量, 天然适合模拟这种标志位域。
 * 优势: 长度可动态扩展, 无需预先计算位宽, API 可读性强。
 * 劣势: 有对象开销, 不如 int + 位运算紧凑, 无法存储多位整数值。
 *
 * @author lingwh
 * @date 2026/08/19 11:30
 */
public class BitSetBitField {

    // 标志位索引, 对应 C 位域中每个字段的位偏移
    private static final int READABLE = 0;
    private static final int WRITABLE = 1;
    private static final int EXECUTABLE = 2;
    private static final int DELETABLE = 3;

    private final BitSet permissionFlags = new BitSet(4);

    public void setReadable(boolean readable) {
        permissionFlags.set(READABLE, readable);
    }

    public void setWritable(boolean writable) {
        permissionFlags.set(WRITABLE, writable);
    }

    public void setExecutable(boolean executable) {
        permissionFlags.set(EXECUTABLE, executable);
    }

    public void setDeletable(boolean deletable) {
        permissionFlags.set(DELETABLE, deletable);
    }

    public boolean isReadable() {
        return permissionFlags.get(READABLE);
    }

    public boolean isWritable() {
        return permissionFlags.get(WRITABLE);
    }

    public boolean isExecutable() {
        return permissionFlags.get(EXECUTABLE);
    }

    public boolean isDeletable() {
        return permissionFlags.get(DELETABLE);
    }

    public void showMemoryLayout() {
        long[] words = permissionFlags.toLongArray();
        System.out.println("long 数组长度: " + words.length);
        for (int i = 0; i < words.length; i++) {
            System.out.println("words[" + i + "] (第" + (i * 64) + "~" + (i * 64 + 63) + "位) = " + Long.toBinaryString(words[i]));
        }
    }
}
