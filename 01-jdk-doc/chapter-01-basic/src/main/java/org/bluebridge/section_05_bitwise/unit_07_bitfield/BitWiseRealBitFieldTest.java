package org.bluebridge.section_05_bitwise.unit_07_bitfield;

/**
 * 位运算模拟 c 语言中的真实的位域测试
 *
 * @author lingwh
 * @date 2026/8/21 9:47
 */
public class BitWiseRealBitFieldTest {

    public static void main(String[] args) {
        int value = 0b00001010;
        int bitOffset = 1;
        int bitCount = 3;

        // ---------- 测试 getBit 读取某一位 ----------
        int flagBit = BitWiseRealBitField.getBit(value, bitOffset);
        System.out.printf("获取 value 中第 %d 位 = %d\n", bitOffset, flagBit, BitWiseRealBitField.showMemoryLayout(value));
        System.out.println("------------------------------");

        // ---------- 测试 getBits 读取某一范围内的位 ----------
        int flagsBit = BitWiseRealBitField.getBits(value, 1, 3);
        System.out.printf("获取 value 中第 %d 到 %d 位, 十进制 = %d, 二进制 = %s\n",
                bitOffset, bitCount, flagsBit, BitWiseRealBitField.showMemoryLayout(value));
        System.out.println("------------------------------");



        // ---------- 测试 setBit 写入某一位 ----------
        bitOffset = 10;
        int writeValue = 1;
        int newValue = BitWiseRealBitField.setBit(value, bitOffset, writeValue);
        System.out.printf("设置 value 中第 %d 位, 写入值 = %d, 写入后十进制 = %d, 二进制 = %s\n",
                bitOffset, writeValue, newValue, BitWiseRealBitField.showMemoryLayout(newValue));
        // 回读校验，同样格式
        int readNewValue = BitWiseRealBitField.getBit(newValue, bitOffset);
        System.out.printf("回读 value 中第 %d 位, 十进制 = %d, 二进制 = %s\n",
                bitOffset, readNewValue, BitWiseRealBitField.showMemoryLayout(newValue));
        System.out.println("------------------------------");

        // ---------- 测试 setBits 写入某一范围内的位 ----------
        // 准备写入的值
        writeValue = 0b111;
        newValue = BitWiseRealBitField.setBits(value, bitOffset, bitCount, writeValue);
        System.out.printf("设置 value 中第 %d 到 %d 位, 写入值 = %d, 写入后十进制 = %d, 二进制 = %s\n",
                bitOffset, bitCount, writeValue, newValue, BitWiseRealBitField.showMemoryLayout(newValue));
        // 回读校验，同样格式
        readNewValue = BitWiseRealBitField.getBits(newValue, bitOffset, bitCount);
        System.out.printf("回读 value 中第 %d 到 %d 位, 十进制 = %d, 二进制 = %s\n",
                bitOffset, bitCount, readNewValue, BitWiseRealBitField.showMemoryLayout(newValue));
        System.out.println("------------------------------");
    }
}