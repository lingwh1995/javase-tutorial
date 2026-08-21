package org.bluebridge.section_05_bitwise.unit_06_bitfield;

/**
 * 使用 int + 位运算模拟 C 语言位域(Bit Field)测试
 *
 * @author lingwh
 * @date 2026/08/19 11:27
 */
public class BitwiseBitFieldTest {

    public static void main(String[] args) {
        BitwiseBitField color = new BitwiseBitField();

        // 打包写入: alpha=255, red=200, green=100, blue=50 (橙色)
        color.setAlpha(255);
        color.setRed(200);
        color.setGreen(100);
        color.setBlue(50);

        System.out.println("--- ARGB 颜色位域模拟 ---");
        System.out.println("打包后的 int 值: " + color.getArgb());
        System.out.println("alpha = " + color.getAlpha()); // 255
        System.out.println("red   = " + color.getRed());   // 200
        System.out.println("green = " + color.getGreen()); // 100
        System.out.println("blue  = " + color.getBlue());  // 50
        System.out.println("十六进制: " + String.format("%08X", color.getArgb()));

        // 演示溢出截断: red 只有 8 位, 写入 300 会被截断为 44
        color.setRed(300); // 300 = 0b100101100, 截断后 = 0b00101100 = 44
        System.out.println();
        System.out.println("写入 red=300 (8位最大值255), 截断后: " + color.getRed()); // 44

        // 内存占用对比
        System.out.println();
        System.out.println("内存占用对比:");
        System.out.println("位域方式: 1 个 int = 4 字节, 存 4 个分量");
        System.out.println("普通方式: 4 个 int = 16 字节, 存 4 个分量");
        System.out.println("节省: 75%");
    }
}