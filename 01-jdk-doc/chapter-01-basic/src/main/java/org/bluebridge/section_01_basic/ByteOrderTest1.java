package org.bluebridge.section_01_basic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 字节序列（大小端）测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ByteOrderTest1 {

    public static void main(String[] args) {
        // Define an integer
        int value = 0x12345678;
        System.out.println("原始数据: " + Integer.toHexString(value));

        // Allocate a ByteBuffer with size of an integer
        ByteBuffer buffer = ByteBuffer.allocate(4);

        // Set the byte order to Big-Endian
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(value);
        System.out.println("大端展示: " + bytesToHex(buffer.array()));

        // Clear the buffer for reuse
        buffer.clear();

        // Set the byte order to Little-Endian
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(value);
        System.out.println("小端展示: " + bytesToHex(buffer.array()));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }
}
