package org.bluebridge.utils;

/**
 * @author lingwh
 * @desc
 * @date 2026/3/14 14:04
 */
public class BitUtils {

    /**
     * 判断是否为奇数
     * @param x
     * @return
     */
    public static boolean isOdd(int x)  {
        return (x & 1) == 1;
    }

    /**
     * 判断是否为偶数
     * @param x
     * @return
     */
    public static boolean isEven(int x) {
        return (x & 1) == 0;
    }

    /**
     * 快速乘2^n
     * @param x
     * @param n
     * @return
     */
    public static int mul2(int x, int n) {
        return x << n;
    }

    /**
     * 快速除2^n
     * @param x
     * @param n
     * @return
     */
    public static int div2(int x, int n) {
        return x >> n;
    }

    /**
     * 判断是否为 2 的幂
     * @param x
     * @return
     */
    public static boolean isPowerOfTwo(int x) {
        return x > 0 && (x & (x - 1)) == 0;
    }

    /**
     * 求绝对值
     * @param x
     * @return
     */
    public static int abs(int x) {
        int sign = x >> 31;
        return (x ^ sign) - sign;
    }

    /**
     * 统治二进制位中的 1 的个数
     * @param x
     * @return
     */
    public static int countBits(int x) {
        int cnt = 0;
        while (x != 0) {
            x &= x - 1;
            cnt++;
        }
        return cnt;
    }

    /**
     * 保留最后一个 1
     * @param x
     * @return
     */
    public static int lastBit(int x) {
        return x & -x;
    }

    /**
     * 清除最后一个 1
     * @param x
     * @return
     */
    public static int clearLastBit(int x) {
        return x & (x - 1);
    }

    /**
     * 高低位交换 - 8 位高低 4 位交换
     * @param x
     * @return
     */
    public static byte swap4Bits(byte x) {
        return (byte) (((x >> 4) & 0x0F) | ((x << 4) & 0xF0));
    }

    /**
     * 高低位交换 - 16 位高低 8 位交换
     * @param x
     * @return
     */
    public static short swap8Bits(short x) {
        return (short) (((x >> 8) & 0xFF) | ((x << 8) & 0xFF00));
    }

    /**
     * 32 位整数字节翻转(大小端)
     * @param x
     * @return
     */
    public static int reverse32(int x) {
        return  ((x >> 24) & 0xFF)       |
                ((x >>  8) & 0xFF00)     |
                ((x <<  8) & 0xFF0000)   |
                ((x << 24) & 0xFF000000);
    }

    /**
     * 8 位全部位翻转
     * @param x
     * @return
     */
    public static byte reverse8(byte x) {
        x = (byte) (((x >> 4) & 0x0F) | ((x << 4) & 0xF0));
        x = (byte) (((x >> 2) & 0x33) | ((x << 2) & 0xCC));
        x = (byte) (((x >> 1) & 0x55) | ((x << 1) & 0xAA));
        return x;
    }

    /**
     * 状态压缩(最常用) - 置位
     * @param x
     * @param i
     * @return
     */
    public static int setBit(int x, int i) {
        return x | (1 << i);
    }

    /**
     * 状态压缩(最常用) - 复位
     * @param x
     * @param i
     * @return
     */
    public static int clearBit(int x, int i) {
        return x & ~(1 << i);
    }

    /**
     * 状态压缩(最常用) - 获取位
     * @param x
     * @param i
     * @return
     */
    public static boolean getBit(int x, int i) {
        return ((x >> i) & 1) == 1;
    }

    /**
     * 状态压缩(最常用) - 反转位
     * @param x
     * @param i
     * @return
     */
    public static int flipBit(int x, int i) {
        return x ^ (1 << i);
    }
}