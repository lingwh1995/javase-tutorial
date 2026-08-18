package org.bluebridge.section_19_jdk19.unit_04_foreign_function;

import org.junit.Test;

/**
 * JDK 19 外部函数和内存 API 预览测试（JEP 424 - Foreign Function & Memory API）
 *     注意：JDK 19 PREVIEW 特性，需要 --enable-preview
 *     FFM API 在 JDK 19 中为预览特性，需要 --enable-preview 编译和运行
 *     涉及的 API：java.lang.foreign.MemorySegment, java.lang.foreign.MemorySession 等
 *
 * 演化历程: FFM API JDK 19(JEP 424, 1st PREVIEW) → JDK 20(JEP 434, 2nd) → JDK 21(JEP 442, 3rd) → JDK 22(JEP 454, 4th)
 *
 * @author lingwh
 * @date 2026/08/06 02:19
 */
public class FFMTest {

    /**
     * 测试使用 MemorySegment 分配和操作堆外内存
     *     注意：MemorySegment.allocateNative(MemorySession) 在 JDK 19 中需要 --enable-preview
     */
    @Test
    public void testMemorySegmentAllocate_Preview() {
        // 使用 try-with-resources 自动管理 MemorySession 生命周期
        // MemorySession 在 JDK 19 中管理内存分配的生命周期
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        try (var session = MemorySession.openConfined()) {
            // 分配 100 字节的堆外内存
            MemorySegment segment = MemorySegment.allocateNative(100, session);
            System.out.println("分配堆外内存大小: " + segment.byteSize() + " 字节");
            System.out.println("内存段地址: " + segment.address());
            System.out.println("内存段是否关闭: " + segment.session().isAlive());
        }
        System.out.println("MemorySession 已自动关闭，内存已释放");
    }

    /**
     * 测试 MemorySegment 读写操作
     *     使用 ValueLayout 定义数据类型布局进行读写
     *     注意：ValueLayout 和 MemorySegment 的访问方法在 JDK 19 中需要 --enable-preview
     */
    @Test
    public void testMemorySegmentReadWrite_Preview() {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        try (var session = MemorySession.openConfined()) {
            // 分配足够容纳 4 个整数的内存
            MemorySegment segment = MemorySegment.allocateNative(4 * 4, session);

            // 写入整数到不同偏移位置
            // 使用 ValueLayout.JAVA_INT 定义 int 类型布局
            segment.set(ValueLayout.JAVA_INT, 0, 42);
            segment.set(ValueLayout.JAVA_INT, 4, 100);
            segment.set(ValueLayout.JAVA_INT, 8, 200);
            segment.set(ValueLayout.JAVA_INT, 12, 300);

            // 从不同偏移位置读取整数
            int val1 = segment.get(ValueLayout.JAVA_INT, 0);
            int val2 = segment.get(ValueLayout.JAVA_INT, 4);
            int val3 = segment.get(ValueLayout.JAVA_INT, 8);
            int val4 = segment.get(ValueLayout.JAVA_INT, 12);

            System.out.println("读取偏移 0: " + val1);
            System.out.println("读取偏移 4: " + val2);
            System.out.println("读取偏移 8: " + val3);
            System.out.println("读取偏移 12: " + val4);
        }
        System.out.println("MemorySession 已自动关闭，堆外内存已释放");
    }

    /**
     * 测试使用 MemorySegment 处理字节数组
     *     通过 MemorySegment.ofArray() 将 Java 数组映射为内存段
     */
    @Test
    public void testMemorySegmentFromArray_Preview() {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 将 Java 数组映射为内存段，可以在堆上操作
        int[] array = {1, 2, 3, 4, 5};
        MemorySegment segment = MemorySegment.ofArray(array);

        System.out.println("数组映射为内存段大小: " + segment.byteSize() + " 字节");

        // 通过 MemorySegment 读取数组元素
        for (int i = 0; i < array.length; i++) {
            int value = segment.get(ValueLayout.JAVA_INT, i * 4);
            System.out.println("元素 " + i + ": " + value);
        }
    }

    /**
     * 测试 MemorySegment 的切片操作
     *     从一个大的内存段中切分出子段
     *     注意：需要 JDK 19 + --enable-preview
     */
    @Test
    public void testMemorySegmentSlice_Preview() {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        try (var session = MemorySession.openConfined()) {
            // 分配一个较大的内存段
            MemorySegment segment = MemorySegment.allocateNative(100, session);

            // 在偏移 20 处切分出一个 30 字节的子段
            MemorySegment slice = segment.asSlice(20, 30);
            System.out.println("原始段大小: " + segment.byteSize() + " 字节");
            System.out.println("切片大小: " + slice.byteSize() + " 字节");
            System.out.println("切片偏移: " + (slice.address().longValue() - segment.address().longValue()) + " 字节");

            // 对切片进行读写操作
            slice.set(ValueLayout.JAVA_INT, 0, 999);
            int readBack = slice.get(ValueLayout.JAVA_INT, 0);
            System.out.println("切片写入/读取值: " + readBack);
        }
        System.out.println("MemorySession 已自动关闭");
    }

    /**
     * 测试使用 MemorySession 管理多个内存段生命周期
     *     MemorySession 统一管理所有在该会话中分配的内存段
     *     注意：需要 JDK 19 + --enable-preview
     */
    @Test
    public void testMemorySessionLifecycle_Preview() {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 创建一个 MemorySession，管理多个内存段
        MemorySession session = MemorySession.openConfined();
        try (session) {
            // 在同一个会话中分配多个内存段
            MemorySegment seg1 = MemorySegment.allocateNative(64, session);
            MemorySegment seg2 = MemorySegment.allocateNative(128, session);
            MemorySegment seg3 = MemorySegment.allocateNative(256, session);

            System.out.println("段 1 大小: " + seg1.byteSize() + " 字节");
            System.out.println("段 2 大小: " + seg2.byteSize() + " 字节");
            System.out.println("段 3 大小: " + seg3.byteSize() + " 字节");
            System.out.println("所有段会话是否活跃: " + session.isAlive());

            // 写入数据到各个段
            seg1.set(ValueLayout.JAVA_INT, 0, 111);
            seg2.set(ValueLayout.JAVA_INT, 0, 222);
            seg3.set(ValueLayout.JAVA_INT, 0, 333);
        }
        // 退出 try-with-resources 后，所有在 session 中分配的内存段都被释放
        System.out.println("会话已关闭，所有堆外内存已释放");
    }

    /**
     * 测试使用 SegmentAllocator 分配内存
     *     SegmentAllocator 提供了更便捷的内存分配方式
     *     注意：需要 JDK 19 + --enable-preview
     */
    @Test
    public void testSegmentAllocator_Preview() {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 使用 SegmentAllocator.implicitAllocator() 获取隐式分配器
        // 但这种分配器需要谨慎使用，因为内存生命周期由 GC 管理
        try (var session = MemorySession.openConfined()) {
            // 通过 Session 创建分配器
            SegmentAllocator allocator = SegmentAllocator.ofSession(session);

            // 使用分配器分配和初始化内存
            MemorySegment segment = allocator.allocate(ValueLayout.JAVA_INT, 42);
            System.out.println("使用分配器分配的内存大小: " + segment.byteSize() + " 字节");
            System.out.println("读取值: " + segment.get(ValueLayout.JAVA_INT, 0));
        }
        System.out.println("内存已释放");
    }

    /**
     * 测试 FFM API 的基本使用
     *     综合演示 MemorySegment 和 MemorySession 的配合使用
     *     注意：需要 JDK 19 + --enable-preview
     */
    @Test
    public void testFFMBasicUsage_Preview() {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 使用 try-with-resources 确保资源释放
        try (MemorySession session = MemorySession.openConfined()) {
            // 分配存储 3 个 double 值的内存
            MemorySegment segment = MemorySegment.allocateNative(3 * 8, session);

            // 写入 double 值
            segment.set(ValueLayout.JAVA_DOUBLE, 0, 3.14);
            segment.set(ValueLayout.JAVA_DOUBLE, 8, 2.718);
            segment.set(ValueLayout.JAVA_DOUBLE, 16, 1.618);

            // 读取并计算
            double sum = 0;
            for (int i = 0; i < 3; i++) {
                double val = segment.get(ValueLayout.JAVA_DOUBLE, i * 8L);
                sum += val;
                System.out.println("值 " + i + ": " + val);
            }
            System.out.println("三数之和: " + sum);
            System.out.println("平均值: " + (sum / 3));
        }
        System.out.println("FFM 基本使用演示完成，资源已释放");
    }
}