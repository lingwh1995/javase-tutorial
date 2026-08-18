package org.bluebridge.section_20_jdk20.unit_05_foreign_function;

import org.junit.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

/**
 * JDK 20 外部函数和内存 API 测试（PREVIEW 特性）
 * @see JEP 434: Foreign Function & Memory API (Second Preview)
 *
 * 演化历程: FFM API JDK 19(1st) → JDK 20(JEP 434, 2nd) → JDK 21(JEP 442, 3rd)
 *
 * @author lingwh
 * @date 2026/08/06 02:19
 */
public class FFMTest {

    /**
     * 测试使用 Arena 分配和释放内存
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testArenaMemoryAllocation_Preview() {
        // 使用 try-with-resources 自动管理 Arena 生命周期
        try (Arena arena = Arena.ofConfined()) {
            // 分配 100 字节的内存
            MemorySegment segment = arena.allocate(100);
            System.out.println("testArenaMemoryAllocation_Preview: 分配内存大小 = " + segment.byteSize() + " 字节");
            System.out.println("testArenaMemoryAllocation_Preview: 内存地址 = " + segment.address());
        }
        // Arena 自动释放，无需手动调用 close()
        System.out.println("testArenaMemoryAllocation_Preview: Arena 已自动关闭，内存已释放");
    }

    /**
     * 测试使用 MemorySegment 写入和读取基本类型数据
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testMemorySegmentReadWrite_Preview() {
        try (Arena arena = Arena.ofConfined()) {
            // 分配 32 字节内存
            MemorySegment segment = arena.allocate(32);

            // 写入 int 值
            segment.set(ValueLayout.JAVA_INT, 0, 42);
            // 写入 long 值
            segment.set(ValueLayout.JAVA_LONG, 8, 123456789L);
            // 写入 double 值
            segment.set(ValueLayout.JAVA_DOUBLE, 16, 3.14159);

            // 读取值
            int intVal = segment.get(ValueLayout.JAVA_INT, 0);
            long longVal = segment.get(ValueLayout.JAVA_LONG, 8);
            double doubleVal = segment.get(ValueLayout.JAVA_DOUBLE, 16);

            System.out.println("testMemorySegmentReadWrite_Preview: int = " + intVal);
            System.out.println("testMemorySegmentReadWrite_Preview: long = " + longVal);
            System.out.println("testMemorySegmentReadWrite_Preview: double = " + doubleVal);
        }
    }

    /**
     * 测试使用 MemorySegment 操作字符串
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testMemorySegmentString_Preview() {
        try (Arena arena = Arena.ofConfined()) {
            String message = "Hello, JDK 20 FFM API!";
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);

            // 分配足够的内存：4 字节长度 + 字符串字节
            MemorySegment segment = arena.allocate(4 + bytes.length);

            // 写入字符串长度
            segment.set(ValueLayout.JAVA_INT, 0, bytes.length);
            // 写入字符串内容
            MemorySegment.copy(bytes, 0, segment, ValueLayout.JAVA_BYTE, 4, bytes.length);

            // 读取字符串长度
            int length = segment.get(ValueLayout.JAVA_INT, 0);
            // 读取字符串内容
            byte[] readBytes = segment.asSlice(4, length).toArray(ValueLayout.JAVA_BYTE);
            String readMessage = new String(readBytes, StandardCharsets.UTF_8);

            System.out.println("testMemorySegmentString_Preview: 写入字符串 = " + message);
            System.out.println("testMemorySegmentString_Preview: 读取字符串 = " + readMessage);
            System.out.println("testMemorySegmentString_Preview: 字符串长度 = " + length);
        }
    }

    /**
     * 测试使用 Arena.ofShared() 创建共享 Arena
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testSharedArena_Preview() {
        // ofShared() 创建的 Arena 可以在多个线程间共享
        try (Arena arena = Arena.ofShared()) {
            MemorySegment segment = arena.allocate(ValueLayout.JAVA_INT, 4);
            segment.set(ValueLayout.JAVA_INT, 0, 2020);
            int value = segment.get(ValueLayout.JAVA_INT, 0);
            System.out.println("testSharedArena_Preview: 共享 Arena 中存储的值 = " + value);
        }
    }

    /**
     * 测试 MemorySegment 填充和复制操作
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testMemorySegmentFillAndCopy_Preview() {
        try (Arena arena = Arena.ofConfined()) {
            // 分配 16 字节并填充 0xAB
            MemorySegment segment = arena.allocate(16);
            segment.fill((byte) 0xAB);

            // 读取验证
            for (int i = 0; i < 16; i++) {
                byte b = segment.get(ValueLayout.JAVA_BYTE, i);
                System.out.print(String.format("%02X ", b));
            }
            System.out.println();

            // 复制到新段
            MemorySegment copy = arena.allocate(16);
            copy.copyFrom(segment);

            // 验证复制结果
            boolean isEqual = segment.mismatch(copy) == -1;
            System.out.println("testMemorySegmentFillAndCopy_Preview: 复制后内容是否一致 = " + isEqual);
        }
    }
}