package org.bluebridge.section_22_jdk22.unit_02_foreign_api;

import org.junit.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.nio.charset.StandardCharsets;

/**
 * JDK 22 外部函数与内存 API 测试(STANDARD 正式特性)
 *
 * 外部函数与内存 API(Foreign Function &amp; Memory API, JEP 454) 是 JDK 22 转正的
 * STANDARD 正式特性, 位于 java.lang.foreign 包下, 提供了安全、可预测地访问堆外内存
 * 和调用外部函数(C 库)的能力, 用于替代 JDK 中不安全、易出错的 sun.misc.Unsafe。
 *
 * 核心 API:
 *   1. Arena: 管理内存的生命周期(自动/手动释放), 取代 Unsafe.allocateMemory
 *   2. MemorySegment: 表示一块连续内存区域(堆外或堆内), 通过内存布局读写
 *   3. ValueLayout: 描述基本类型的内存布局(JAVA_INT、JAVA_LONG 等)
 *   4. StructLayout: 描述结构体布局(多个字段的组合)
 *   5. Linker: 调用外部函数(downcall)和导出 Java 方法(upcall)
 *   6. SymbolLookup: 在外部库中查找符号(函数)
 *
 * 演化历程: FFM API JDK 14(JEP 370/383/389, Incubator) → JDK 17-21(JEP 412/419/424/442, Incubator) → JDK 22(JEP 454, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/18 09:10
 */
public class ForeignFunctionMemoryTest {

    /**
     * 测试 Arena 分配堆外内存并读写(STANDARD)
     * 使用 try-with-resources 自动释放内存, 无需手动 free
     */
    @Test
    public void testArenaAllocateAndReadWrite() {
        // ===== 旧版实现方式(JDK 22 之前): 使用 sun.misc.Unsafe 手动分配/释放内存, 极易出错 =====
        // Unsafe unsafe = Unsafe.getUnsafe();
        // long addr = unsafe.allocateMemory(4);
        // unsafe.putInt(addr, 42);
        // int value = unsafe.getInt(addr);
        // unsafe.freeMemory(addr);
        // ===== 新版实现方式(JDK 22 起): Arena + MemorySegment 安全地管理内存 =====
        try (Arena arena = Arena.ofConfined()) {
            // 在 Arena 中分配一块容纳 int 的内存
            MemorySegment segment = arena.allocate(ValueLayout.JAVA_INT);
            // 写入 int 值
            segment.set(ValueLayout.JAVA_INT, 0, 42);
            // 从内存段中读取
            int value = segment.get(ValueLayout.JAVA_INT, 0);
            System.out.println("Arena 分配内存读写测试:");
            System.out.println("  内存段字节大小: " + segment.byteSize());
            System.out.println("  写入 42 后读取: " + value);
        }
        // Arena 关闭后, 分配的内存自动释放
        System.out.println("  Arena 已自动释放堆外内存");
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 MemorySegment 一次性写入多种类型数据(STANDARD)
     * 在同一个内存段内按偏移写入不同基本类型
     */
    @Test
    public void testMemorySegmentMultiTypes() {
        try (Arena arena = Arena.ofConfined()) {
            // 分配一块 24 字节的内存段
            MemorySegment segment = arena.allocate(24);
            // 按偏移写入不同类型数据
            segment.set(ValueLayout.JAVA_INT, 0, 100);       // 0-3: int
            segment.set(ValueLayout.JAVA_LONG, 8, 888888L);  // 8-15: long
            segment.set(ValueLayout.JAVA_DOUBLE, 16, 3.14);  // 16-23: double

            // 按偏移读取
            int intValue = segment.get(ValueLayout.JAVA_INT, 0);
            long longValue = segment.get(ValueLayout.JAVA_LONG, 8);
            double doubleValue = segment.get(ValueLayout.JAVA_DOUBLE, 16);

            System.out.println("MemorySegment 多类型读写测试:");
            System.out.println("  内存段大小: " + segment.byteSize() + " 字节");
            System.out.println("  int 值: " + intValue);
            System.out.println("  long 值: " + longValue);
            System.out.println("  double 值: " + doubleValue);
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 StructLayout 结构体内存布局(STANDARD)
     * 使用 StructLayout 描述 C 结构体, 方便与外部内存交互
     */
    @Test
    public void testStructLayout() {
        // 定义一个 Point 结构体: { int x; int y; }
        StructLayout pointLayout = MemoryLayout.structLayout(
                ValueLayout.JAVA_INT.withName("x"),
                ValueLayout.JAVA_INT.withName("y"));
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment point = arena.allocate(pointLayout);
            // 按字段名写入(内部使用偏移量计算)
            point.set(ValueLayout.JAVA_INT, 0, 10);
            point.set(ValueLayout.JAVA_INT, 4, 20);

            int x = point.get(ValueLayout.JAVA_INT, 0);
            int y = point.get(ValueLayout.JAVA_INT, 4);
            System.out.println("StructLayout 结构体测试:");
            System.out.println("  结构体总大小: " + pointLayout.byteSize() + " 字节");
            System.out.println("  Point(x=" + x + ", y=" + y + ")");
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 MemorySegment 与 Java 字符串互转(STANDARD)
     * setString 写入 UTF-8 字符串, getString 读取
     */
    @Test
    public void testMemorySegmentString() {
        try (Arena arena = Arena.ofConfined()) {
            // 分配足够容纳 UTF-8 字符串(含结尾 \0)的内存
            String text = "Hello FFM API";
            MemorySegment segment = arena.allocate(
                    text.getBytes(StandardCharsets.UTF_8).length + 1);
            // 写入字符串(自动补充结尾 \0)
            segment.setString(0, text, StandardCharsets.UTF_8);
            // 读取字符串
            String readBack = segment.getString(0, StandardCharsets.UTF_8);
            System.out.println("MemorySegment 字符串互转测试:");
            System.out.println("  写入: " + text);
            System.out.println("  读取: " + readBack);
            System.out.println("  字节大小: " + segment.byteSize() + "(含结尾 \\0)");
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Linker 调用外部 C 库函数(STANDARD)
     * 通过 SymbolLookup + Linker.downcallHandle 调用 C 库中的 strlen 函数
     */
    @Test
    public void testLinkerCallCFunction() {
        // ===== 旧版实现方式(JDK 22 之前): 只能借助 JNI/JNA/JNR 等, 编写繁琐且需要原生代码 =====
        // ===== 新版实现方式(JDK 22 起): 使用 java.lang.foreign.Linker 直接调用 =====
        try (Arena arena = Arena.ofConfined()) {
            // 构造 strlen 函数描述: size_t strlen(const char* str)
            FunctionDescriptor strlenDesc = FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);
            // 使用默认链接器查找并绑定函数
            Linker linker = Linker.nativeLinker();
            SymbolLookup stdlib = linker.defaultLookup();
            // 分配字符串内存并写入(含结尾 \0)
            MemorySegment str = arena.allocate(
                    "Hello FFM API".getBytes(StandardCharsets.UTF_8).length + 1);
            str.setString(0, "Hello FFM API", StandardCharsets.UTF_8);
            try {
                MethodHandle strlen = linker.downcallHandle(
                        stdlib.find("strlen").orElseThrow(),
                        strlenDesc);
                long length = (long) strlen.invoke(str);
                System.out.println("Linker 调用 C 库 strlen 测试:");
                System.out.println("  strlen(\"Hello FFM API\") = " + length);
            } catch (Throwable e) {
                // 不同平台默认查找的库可能不同, 找不到符号时仅提示
                System.out.println("Linker 调用 strlen 失败: " + e.getMessage());
            }
        }
        System.out.println("--- 分割线 ---");
    }
}
