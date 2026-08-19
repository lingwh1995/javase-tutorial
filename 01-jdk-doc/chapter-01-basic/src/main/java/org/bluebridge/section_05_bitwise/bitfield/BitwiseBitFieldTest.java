package org.bluebridge.section_05_bitwise.bitfield;

/**
 * 位域测试
 *
 * @author lingwh
 * @date 2026/08/19 11:20
 */
public class BitwiseBitFieldTest {

    public static void main(String[] args) {
        BitwiseBitField bitField = new BitwiseBitField();

        // 打包写入: type=5, level=20, code=200, reserved=10000
        bitField.setType(5);
        bitField.setLevel(20);
        bitField.setCode(200);
        bitField.setReserved(10000);

        System.out.println("--- C 语言位域模拟 ---");
        System.out.println("打包后的 int 值: " + bitField.getPacked());
        System.out.println("type     = " + bitField.getType());     // 5
        System.out.println("level    = " + bitField.getLevel());    // 20
        System.out.println("code     = " + bitField.getCode());     // 200
        System.out.println("reserved = " + bitField.getReserved()); // 10000
        System.out.println("二进制: " + Integer.toBinaryString(bitField.getPacked()));

        // 演示溢出截断: type 只有 3 位, 写入 9 会被截断为 1
        bitField.setType(9); // 9 = 0b1001, 截断后 = 0b001 = 1
        System.out.println();
        System.out.println("写入 type=9 (3位最大值7), 截断后: " + bitField.getType()); // 1

        // 演示内存占用: 4 个字段共 32 位 = 4 字节, 而非 4 个 int = 16 字节
        System.out.println();
        System.out.println("内存占用对比:");
        System.out.println("位域方式: 1 个 int = 4 字节");
        System.out.println("普通方式: 4 个 int = 16 字节");
        System.out.println("节省: 75%");
    }
}
