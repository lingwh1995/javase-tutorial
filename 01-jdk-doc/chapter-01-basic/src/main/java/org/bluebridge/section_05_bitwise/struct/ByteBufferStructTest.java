package org.bluebridge.section_05_bitwise.struct;

/**
 * 结构体测试
 *
 * @author lingwh
 * @date 2026/08/19 11:48
 */
public class ByteBufferStructTest {

    public static void main(String[] args) {
        System.out.println("--- 使用 ByteBuffer 模拟 C 结构体内存布局 ---");

        ByteBufferStruct struct = new ByteBufferStruct();
        struct.setId(1);
        struct.setName("张三");
        struct.setAge(20);
        struct.setScore(89.5f);

        System.out.println("id    = " + struct.getId());
        System.out.println("name  = " + struct.getName());
        System.out.println("age   = " + struct.getAge());
        System.out.println("score = " + struct.getScore());

        // 打印内存布局
        byte[] data = struct.toByteArray();
        System.out.println();
        System.out.println("--- 内存布局 (" + data.length + " 字节): ---");
        System.out.println("偏移 0-3   (id):    " + toHex(data, 0, 4));
        System.out.println("偏移 4-19  (name):  " + toHex(data, 4, 16));
        System.out.println("偏移 20-23 (age):   " + toHex(data, 20, 4));
        System.out.println("偏移 24-27 (score): " + toHex(data, 24, 4));

        System.out.println();
        System.out.println("--- 与 C 结构体的对比 ---");
        System.out.println("C 结构体:  内存连续, 无对象头, 可直接 memcpy");
        System.out.println("ByteBuffer: 内存连续, 无对象头, 精确控制偏移");
        System.out.println("普通类:     内存不连续, 有对象头, 无法控制布局");
    }

    private static String toHex(byte[] data, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset + length; i++) {
            sb.append(String.format("%02X ", data[i] & 0xFF));
        }
        return sb.toString().trim();
    }
}
