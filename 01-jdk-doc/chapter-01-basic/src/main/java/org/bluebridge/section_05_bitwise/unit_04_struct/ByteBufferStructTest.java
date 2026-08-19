package org.bluebridge.section_05_bitwise.unit_04_struct;

/**
 * 结构体测试
 *
 * @author lingwh
 * @date 2026/08/19 11:48
 */
public class ByteBufferStructTest {

    public static void main(String[] args) {
        System.out.println("--- 使用 ByteBuffer 模拟 C 结构体内存布局 ---");

        ByteBufferStruct student = new ByteBufferStruct();
        student.setId(1);
        student.setName("张三");
        student.setAge(20);
        student.setScore(89.5f);

        System.out.println("id    = " + student.getId());
        System.out.println("name  = " + student.getName());
        System.out.println("age   = " + student.getAge());
        System.out.println("score = " + student.getScore());

        // 打印内存布局
        byte[] data = student.toByteArray();
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
