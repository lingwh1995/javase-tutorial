package org.bluebridge.section_21_jdk21.unit_09_ffm;

import org.junit.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

/**
 * JDK 21 外部函数和内存 API 测试(PREVIEW 预览特性)
 *
 * 外部函数和内存 API(Foreign Function & Memory API, JEP 442, 第三次预览)
 * 是 JDK 21 的 PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
 *
 * 该 API 替代了原有的 JNI(Java Native Interface), 提供了更安全、更高效
 * 的与外部代码和内存交互的方式:
 *   1. MemorySegment: 内存段的抽象, 表示一段连续的内存区域
 *   2. Arena: 内存分配器, 管理 MemorySegment 的生命周期
 *   3. ValueLayout: 值布局, 描述基本数据类型在内存中的布局
 *   4. Linker: 链接器, 用于调用外部函数
 *
 * 演化历程:
 *   - JDK 19: JEP 424 第一次预览(孵化器)
 *   - JDK 20: JEP 434 第二次预览
 *   - JDK 21: JEP 442 第三次预览
 *   - JDK 22: JEP 454 转正(最终确定的 API)
 *
 * @author lingwh
 * @date 2026/08/06 18:18
 */
public class FFMTest {

    /**
     * 测试 MemorySegment 和 Arena 的基本使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 使用 Arena 分配内存, 通过 MemorySegment 操作内存
     */
    @Test
    public void testBasicMemorySegment_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 使用 Arena.ofAuto() 创建自动管理的内存分配器
        try (Arena arena = Arena.ofConfined()) {
            // 分配 100 字节的内存
            MemorySegment segment = arena.allocate(100);
            System.out.println("分配内存段:");
            System.out.println("  字节大小: " + segment.byteSize());
            System.out.println("  是否原生: " + segment.isNative());
            System.out.println("  地址: " + segment.address());
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 ValueLayout 读写内存(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * ValueLayout 定义了基本数据类型的内存布局, 用于读写 MemorySegment
     */
    @Test
    public void testValueLayoutReadWrite_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        try (Arena arena = Arena.ofConfined()) {
            // 分配 4 字节内存用于存储 int 值
            MemorySegment segment = arena.allocate(ValueLayout.JAVA_INT);
            // 写入 int 值
            segment.set(ValueLayout.JAVA_INT, 0, 42);
            // 读取 int 值
            int value = segment.get(ValueLayout.JAVA_INT, 0);
            System.out.println("写入/读取 int 值: " + value);
            System.out.println("--------------------------------------");

            // 分配 8 字节内存用于存储 long 值
            MemorySegment longSegment = arena.allocate(ValueLayout.JAVA_LONG);
            longSegment.set(ValueLayout.JAVA_LONG, 0, 123456789L);
            long longValue = longSegment.get(ValueLayout.JAVA_LONG, 0);
            System.out.println("写入/读取 long 值: " + longValue);
            System.out.println("--------------------------------------");

            // 分配 8 字节内存用于存储 double 值
            MemorySegment doubleSegment = arena.allocate(ValueLayout.JAVA_DOUBLE);
            doubleSegment.set(ValueLayout.JAVA_DOUBLE, 0, 3.14159);
            double doubleValue = doubleSegment.get(ValueLayout.JAVA_DOUBLE, 0);
            System.out.println("写入/读取 double 值: " + doubleValue);
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 Arena 的几种类型(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * Arena 分为: ofConfined(受限, 单线程), ofAuto(自动管理), ofShared(共享)
     */
    @Test
    public void testArenaTypes_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 1. ofConfined - 受限 Arena, 只能在创建线程使用
        System.out.println("1. Arena.ofConfined() - 受限 Arena:");
        try (Arena confined = Arena.ofConfined()) {
            MemorySegment segment = confined.allocate(64);
            System.out.println("   分配了 " + segment.byteSize() + " 字节");
        }
        System.out.println("   受限 Arena 已自动关闭");
        System.out.println("--------------------------------------");

        // 2. ofAuto - 自动 Arena, 由 GC 自动管理
        System.out.println("2. Arena.ofAuto() - 自动 Arena:");
        Arena autoArena = Arena.ofAuto();
        MemorySegment autoSegment = autoArena.allocate(128);
        System.out.println("   分配了 " + autoSegment.byteSize() + " 字节");
        System.out.println("   (由 GC 自动回收, 无需手动关闭)");
        System.out.println("--------------------------------------");

        // 3. ofShared - 共享 Arena, 可以在多线程中使用
        System.out.println("3. Arena.ofShared() - 共享 Arena:");
        try (Arena shared = Arena.ofShared()) {
            MemorySegment sharedSegment = shared.allocate(256);
            System.out.println("   分配了 " + sharedSegment.byteSize() + " 字节");
        }
        System.out.println("   共享 Arena 已自动关闭");
    }

    /**
     * 测试 MemorySegment 的切片操作(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * MemorySegment 支持切片操作, 可以获取子内存段
     */
    @Test
    public void testMemorySegmentSlice_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        try (Arena arena = Arena.ofConfined()) {
            // 分配 32 字节内存
            MemorySegment segment = arena.allocate(32);

            // 写入一些数据
            for (int i = 0; i < 8; i++) {
                segment.set(ValueLayout.JAVA_INT, i * 4L, i * 10);
            }

            // 读取原始数据
            System.out.println("原始数据:");
            for (int i = 0; i < 8; i++) {
                int val = segment.get(ValueLayout.JAVA_INT, i * 4L);
                System.out.println("  [" + i + "] = " + val);
            }
            System.out.println("--------------------------------------");

            // 切片: 从偏移 8 字节开始, 取 16 字节
            MemorySegment slice = segment.asSlice(8, 16);
            System.out.println("切片(偏移 8, 大小 16):");
            System.out.println("  切片大小: " + slice.byteSize());
            for (int i = 0; i < 4; i++) {
                int val = slice.get(ValueLayout.JAVA_INT, i * 4L);
                System.out.println("  [" + i + "] = " + val);
            }
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 MemorySegment 复制操作(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 支持内存段之间的复制和与 Java 数组的互操作
     */
    @Test
    public void testMemorySegmentCopy_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        try (Arena arena = Arena.ofConfined()) {
            // 从 Java 数组复制到 MemorySegment
            int[] sourceArray = {1, 2, 3, 4, 5};
            MemorySegment segment = arena.allocate(5 * 4L); // 5 个 int
            segment.copyFrom(MemorySegment.ofArray(sourceArray));

            // 读取并验证
            System.out.println("从数组复制到 MemorySegment:");
            for (int i = 0; i < 5; i++) {
                int val = segment.get(ValueLayout.JAVA_INT, i * 4L);
                System.out.println("  [" + i + "] = " + val);
            }
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 MemorySegment 的字符串操作(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 使用 MemorySegment 存储和读取字符串
     */
    @Test
    public void testMemorySegmentString_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        try (Arena arena = Arena.ofConfined()) {
            // 存储字符串
            String text = "Hello, FFM API!";
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            MemorySegment segment = arena.allocate(bytes.length + 1);
            // 复制字节数据
            for (int i = 0; i < bytes.length; i++) {
                segment.set(ValueLayout.JAVA_BYTE, i, bytes[i]);
            }
            // 末尾添加 null 终止符
            segment.set(ValueLayout.JAVA_BYTE, bytes.length, (byte) 0);

            // 读取字符串
            byte[] readBytes = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                readBytes[i] = segment.get(ValueLayout.JAVA_BYTE, i);
            }
            String readText = new String(readBytes, StandardCharsets.UTF_8);
            System.out.println("写入字符串: " + text);
            System.out.println("读取字符串: " + readText);
        }
        System.out.println("--------------------------------------");
    }
}
