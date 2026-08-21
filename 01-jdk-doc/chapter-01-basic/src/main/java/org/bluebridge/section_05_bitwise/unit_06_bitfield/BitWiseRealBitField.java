package org.bluebridge.section_05_bitwise.unit_06_bitfield;

/**
 * 位运算模拟 c 语言中的真实的位域
 *
 * @author lingwh
 * @date 2026/8/21 9:14
 */
public class BitWiseRealBitField {

    /**
     * 模拟 c 语言位域读取任意范围内的位
     *
     * @param value 原始整数
     * @param bitOffset 起始bit偏移(从0开始)
     * @param bitCount 要取多少bit
     * @return 提取出来的值
     */
    public static int getBits(int value, int bitOffset, int bitCount) {
        /**
         * 获取掩码过程，以 bitCount = 3 为例
         * mask = 1                 ->  0000 0001
         * mask =  mask << bitCount ->  0000 1000
         * mask =  mask - 1         ->  0000 0111
         */
        int mask = (1 << bitCount) - 1;

        /**
         * 计算过程，以 value = 0b00001010 且 bitOffset = 1 为例
         * value = value >> bitOffset    ->  0000 0101
         * value = value & mask          ->  0000 0101 &  0000 0111 -> 0000 0101
         */
        return (value >> bitOffset) & mask;
    }

    /**
     * 模拟 c 语言位域读取某一位
     *
     * @param value 原始整数
     * @param bitOffset 起始bit偏移(从0开始)
     * @return 提取出来的值
     */
    public static int getBit(int value, int bitOffset) {
        return getBits(value, bitOffset, 1);
    }

    /**
     * 模拟 c 语言位域设置某一范围内的位
     *
     * @param originalValue 原始数据
     * @param bitOffset 起始偏移
     * @param bitCount 位数
     * @param writeValue 要写入的值
     * @return 修改后新数值
     */
    public static int setBits(int originalValue, int bitOffset, int bitCount, int writeValue) {
        /**
         * 获取掩码过程，以 bitCount = 3 为例
         * mask = 1                     -> 0000 0001
         * mask = mask << bitCount      -> 0000 1000
         * mask = mask - 1              -> 0000 0111
         */
        int mask = (1 << bitCount) - 1;

        /**
         * 清空目标bit段，示例：mask=0b00000111，bitOffset=1
         * mask << bitOffset            -> 0000 1110
         * ~(mask << bitOffset)         -> 1111 0001  按位取反
         * originalValue = originalValue & ~(mask << bitOffset)  将目标3个bit全部置0，其余bit保持不变
         */
        int result = originalValue & ~(mask << bitOffset);

        /**
         * 将写入值截断后移位，再或运算写入目标bit位
         * writeValue & mask：截断writeValue，只保留bitCount位，防止高位污染其他bit
         * (writeValue & mask) << bitOffset：把数值移动到目标bit偏移位置
         * result = result | (...)：把新bit值合并到原始数据中
         * 示例：writeValue=0b101，bitOffset=1，bitCount=3
         * writeValue & mask                 -> 0000 0101
         * (writeValue & mask) << bitOffset  -> 0000 1010
         * result = result | 00001010        -> 将101写入bit1~bit3
         */
        result = result | ((writeValue & mask) << bitOffset);
        return result;
    }

    /**
     * 模拟 c 语言位域设置某一位位
     *
     * @param originalValue 原始数据
     * @param bitOffset 起始偏移
     * @param writeValue 要写入的值
     * @return 修改后新数值
     */
    public static int setBit(int originalValue, int bitOffset, int writeValue) {
        if(writeValue != 0 && writeValue != 1) {
            throw new IllegalArgumentException("writeValue 的值只能是 0 或 1，实际传入的值是 " + writeValue);
        }
        return setBits(originalValue, bitOffset, 1, writeValue);
    }

    /**
     * 补全到8位二进制，左侧补0
     *
     * @param value 要展示的整数
     * @return 8位二进制字符串
     */
    public static String showMemoryLayout(int value) {
        String bin = Integer.toBinaryString(value);
        // 截取后8位，不足8位前面补0
        return String.format("%8s", bin).replace(' ', '0');
    }
}
