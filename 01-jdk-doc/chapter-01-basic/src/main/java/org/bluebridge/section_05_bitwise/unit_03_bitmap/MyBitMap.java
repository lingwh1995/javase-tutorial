package org.bluebridge.section_05_bitwise.unit_03_bitmap;

/**
*  自定义位图类
 *
 * @author lingwh
 * @date 2026/3/14 15:09
 */
public class MyBitMap {

    private final int[] bits;
    // int 类型占 32 位
    private static final int UNIT = 32;

    // 初始化位图：max 表示能存储的最大数字
    public MyBitMap(int max) {
        bits = new int[max / UNIT + 1];
    }

    // 设置位：标记 num 存在
    public void set(int num) {
        int arrayIndex = num / UNIT;
        int bitOffset = num % UNIT;
        // 位运算：将对应位设为 1
        bits[arrayIndex] |= (1 << bitOffset);
    }

    // 查询位：判断 num 是否存在
    public boolean get(int num) {
        int arrayIndex = num / UNIT;
        int bitOffset = num % UNIT;
        return (bits[arrayIndex] & (1 << bitOffset)) != 0;
    }

    // 清除位：标记 num 不存在
    public void clear(int num) {
        int arrayIndex = num / UNIT;
        int bitOffset = num % UNIT;
        bits[arrayIndex] &= ~(1 << bitOffset);
    }
}
