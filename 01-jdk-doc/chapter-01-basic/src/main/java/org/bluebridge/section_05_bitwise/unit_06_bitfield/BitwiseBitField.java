package org.bluebridge.section_05_bitwise.unit_06_bitfield;

/**
 * 使用 int + 位运算模拟 C 语言位域(Bit Field)
 *
 * 以 ARGB 颜色为例, 将 4 个 0-255 的分量打包到一个 int 中:
 *
 * C 语言位域:
 *   struct Color {
 *       unsigned int alpha : 8;   // 透明度, 0-255
 *       unsigned int red   : 8;   // 红色, 0-255
 *       unsigned int green : 8;   // 绿色, 0-255
 *       unsigned int blue  : 8;   // 蓝色, 0-255
 *   };  // 共 32 位 = 4 字节, 紧凑存储在一个 int 中
 *
 * 这正是 java.awt.Color.getRGB() 的底层实现方式。
 *
 * @author lingwh
 * @date 2026/08/19 11:10
 */
public class BitwiseBitField {

    // 各分量的位宽
    private static final int ALPHA_BITS = 8;
    private static final int RED_BITS   = 8;
    private static final int GREEN_BITS = 8;
    private static final int BLUE_BITS  = 8;

    // 各分量的位偏移(从低位开始)
    private static final int ALPHA_OFFSET = RED_BITS + GREEN_BITS + BLUE_BITS; // 24
    private static final int RED_OFFSET   = GREEN_BITS + BLUE_BITS;          // 16
    private static final int GREEN_OFFSET = BLUE_BITS;                       // 8
    private static final int BLUE_OFFSET  = 0;

    // 各分量的掩码
    private static final int ALPHA_MASK = (1 << ALPHA_BITS) - 1; // 0x000000FF
    private static final int RED_MASK   = (1 << RED_BITS) - 1;   // 0x000000FF
    private static final int GREEN_MASK = (1 << GREEN_BITS) - 1;  // 0x000000FF
    private static final int BLUE_MASK  = (1 << BLUE_BITS) - 1;  // 0x000000FF

    // 打包后的 ARGB 值
    private int argb;

    // ===== 写入(打包) =====

    public void setAlpha(int alpha) {
        argb = (argb & ~(ALPHA_MASK << ALPHA_OFFSET)) | ((alpha & ALPHA_MASK) << ALPHA_OFFSET);
    }

    public void setRed(int red) {
        argb = (argb & ~(RED_MASK << RED_OFFSET)) | ((red & RED_MASK) << RED_OFFSET);
    }

    public void setGreen(int green) {
        argb = (argb & ~(GREEN_MASK << GREEN_OFFSET)) | ((green & GREEN_MASK) << GREEN_OFFSET);
    }

    public void setBlue(int blue) {
        argb = (argb & ~(BLUE_MASK << BLUE_OFFSET)) | ((blue & BLUE_MASK) << BLUE_OFFSET);
    }

    // ===== 读取(解包) =====

    public int getAlpha() {
        return (argb >> ALPHA_OFFSET) & ALPHA_MASK;
    }

    public int getRed() {
        return (argb >> RED_OFFSET) & RED_MASK;
    }

    public int getGreen() {
        return (argb >> GREEN_OFFSET) & GREEN_MASK;
    }

    public int getBlue() {
        return (argb >> BLUE_OFFSET) & BLUE_MASK;
    }

    public int getArgb() {
        return argb;
    }
}
