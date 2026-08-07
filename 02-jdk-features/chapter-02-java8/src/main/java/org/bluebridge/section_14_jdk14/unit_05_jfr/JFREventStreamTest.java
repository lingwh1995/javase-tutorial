package org.bluebridge.section_14_jdk14.unit_05_jfr;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDK 14 JFR 事件流（JEP 349，STANDARD 正式特性）
 *
 * JFR（Java Flight Recorder）事件流允许开发者以编程方式实时订阅 JFR 事件，
 * 无需使用 JMC（Java Mission Control）工具即可消费 JFR 数据。
 * JEP 349 在 JDK 14 中是 STANDARD 正式特性，无需 --enable-preview。
 *
 * 主要 API:
 * - jdk.jfr.consumer.RecordingStream: 事件流的主要入口
 * - 支持 onEvent() 方法订阅特定事件
 * - 支持过滤器和时间窗口
 *
 * 运行说明：
 * 运行此测试需要启用 JFR 参数，请在 VM options 中添加：
 * -XX:StartFlightRecording=duration=60s,filename=recording.jfr
 *
 * 或者使用以下完整参数：
 * -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=recording.jfr
 *
 * 注意：JFR 事件流是 JDK 14 的 STANDARD 正式特性，不需要 --enable-preview。
 * 但需要开启 JFR 录制功能才能使用。
 *
 * 演化历程: JFR 事件流 JDK 14 STANDARD（JEP 349），无预览历程
 *
 * @author lingwh
 * @date 2026/08/06 14:08
 */
public class JFREventStreamTest {

    private static final int EVENT_TIMEOUT_SECONDS = 5;

    /**
     * 测试使用 RecordingStream 订阅 JFR 事件
     *
     * 启动 RecordingStream 并订阅 jdk.GarbageCollection 事件，
     * 通过手动触发 GC 来验证事件流能够正常接收 JFR 事件。
     *
     * 运行前请确保在 VM options 中添加了 JFR 相关参数：
     * -XX:StartFlightRecording=duration=60s,filename=recording.jfr
     */
    @Test
    public void testRecordingStreamGCEvent() throws Exception {
        System.out.println("=== 测试 RecordingStream 订阅 GC 事件 ===");
        System.out.println("注意: 运行此测试需要在 VM options 中添加 JFR 参数");
        System.out.println("例如: -XX:StartFlightRecording=duration=60s,filename=recording.jfr");
        System.out.println();

        // 使用 CountDownLatch 等待事件到达
        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger eventCount = new AtomicInteger(0);

        // 创建 RecordingStream
        jdk.jfr.consumer.RecordingStream recordingStream = new jdk.jfr.consumer.RecordingStream();

        // 订阅 GC 事件
        recordingStream.onEvent("jdk.GarbageCollection", event -> {
            int count = eventCount.incrementAndGet();
            System.out.println("收到 GC 事件 #" + count);
            System.out.println("  事件类型: " + event.getEventType().getName());
            System.out.println("  开始时间: " + event.getStartTime());
            System.out.println("  持续时间: " + event.getDuration());
            System.out.println("  GC ID: " + event.getValue("gcId"));
            System.out.println("  原因: " + event.getValue("cause"));

            if (count >= 2) {
                latch.countDown();
            }
        });

        // 订阅 CPU 使用率事件
        recordingStream.onEvent("jdk.CPULoad", event -> {
            System.out.println("收到 CPU Load 事件:");
            System.out.println("  JVM 使用率: " + event.getValue("jvmUser"));
            System.out.println("  系统使用率: " + event.getValue("machineTotal"));
        });

        // 订阅活跃记录事件
        recordingStream.onEvent("jdk.ActiveRecording", event -> {
            System.out.println("收到 ActiveRecording 事件:");
            System.out.println("  记录名称: " + event.getValue("name"));
            System.out.println("  记录 ID: " + event.getValue("id"));
        });

        // 启动流
        recordingStream.startAsync();

        // 触发 GC 产生事件
        System.out.println("触发 GC...");
        for (int i = 0; i < 3; i++) {
            System.gc();
            System.gc();
            Thread.sleep(1000);
        }

        // 等待事件到达，最多等待指定时间
        boolean eventReceived = latch.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        System.out.println("GC 事件接收状态: " + (eventReceived ? "成功" : "超时"));
        System.out.println("共收到事件数: " + eventCount.get());

        // 关闭流
        recordingStream.close();
        System.out.println("RecordingStream 已关闭");
    }

    /**
     * 测试使用 RecordingStream 订阅 JVM 信息事件
     *
     * 订阅 jdk.InitialSystemProperty、jdk.JVMInformation 等事件，
     * 获取 JVM 启动时的系统属性和 JVM 信息。
     */
    @Test
    public void testRecordingStreamJVMInfo() throws Exception {
        System.out.println("=== 测试 RecordingStream 订阅 JVM 信息事件 ===");

        CountDownLatch latch = new CountDownLatch(1);
        jdk.jfr.consumer.RecordingStream recordingStream = new jdk.jfr.consumer.RecordingStream();

        // 订阅 JVM 信息事件
        recordingStream.onEvent("jdk.JVMInformation", event -> {
            System.out.println("收到 JVMInformation 事件:");
            System.out.println("  JVM 名称: " + event.getValue("jvmName"));
            System.out.println("  JVM 版本: " + event.getValue("jvmVersion"));
            System.out.println("  JVM 参数: " + event.getValue("jvmArguments"));
            System.out.println("  JVM 启动时间: " + event.getValue("jvmStartTime"));
            latch.countDown();
        });

        // 订阅初始化系统属性事件
        recordingStream.onEvent("jdk.InitialSystemProperty", event -> {
            System.out.println("收到 InitialSystemProperty 事件:");
            System.out.println("  Key: " + event.getValue("key") + " = " + event.getValue("value"));
        });

        // 订阅操作系统信息事件
        recordingStream.onEvent("jdk.OSInformation", event -> {
            System.out.println("收到 OSInformation 事件:");
            System.out.println("  操作系统: " + event.getValue("osVersion"));
        });

        // 启动流
        recordingStream.startAsync();

        // 等待事件，最多等待指定时间
        boolean eventReceived = latch.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        System.out.println("JVM 信息事件接收状态: " + (eventReceived ? "成功" : "超时"));

        // 关闭流
        recordingStream.close();
        System.out.println("RecordingStream 已关闭");
    }

    /**
     * 测试使用 RecordingStream 订阅线程分配事件
     *
     * 通过分配大量对象来触发 jdk.ThreadAllocationStatistics 事件，
     * 验证 JFR 事件流能够捕获和分析线程级别的内存分配情况。
     */
    @Test
    public void testRecordingStreamThreadAllocation() throws Exception {
        System.out.println("=== 测试 RecordingStream 订阅线程分配事件 ===");

        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger eventCount = new AtomicInteger(0);

        jdk.jfr.consumer.RecordingStream recordingStream = new jdk.jfr.consumer.RecordingStream();

        // 订阅线程分配统计事件
        recordingStream.onEvent("jdk.ThreadAllocationStatistics", event -> {
            int count = eventCount.incrementAndGet();
            String threadName = event.getValue("threadName") != null ?
                    event.getValue("threadName").toString() : "unknown";
            System.out.println("收到 ThreadAllocationStatistics 事件 #" + count);
            System.out.println("  线程名称: " + threadName);
            System.out.println("  分配大小: " + event.getValue("allocated") + " 字节");
            System.out.println("  线程 ID: " + event.getValue("threadId"));

            if (count >= 3) {
                latch.countDown();
            }
        });

        // 启动流
        recordingStream.startAsync();

        // 分配对象产生事件
        System.out.println("分配对象以触发线程分配事件...");
        Object[] objects = new Object[10000];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new byte[1024];
        }

        // 等待事件
        boolean eventReceived = latch.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        System.out.println("线程分配事件接收状态: " + (eventReceived ? "成功" : "超时"));
        System.out.println("共收到线程分配事件数: " + eventCount.get());

        recordingStream.close();
        System.out.println("RecordingStream 已关闭");
    }

    /**
     * 测试使用 RecordingStream 过滤特定事件类型
     *
     * 演示如何通过 onEvent 方法过滤特定名称的事件，
     * 以及使用 setMaxAge 和 setMaxSize 控制事件流的数据量。
     */
    @Test
    public void testRecordingStreamWithFilter() throws Exception {
        System.out.println("=== 测试 RecordingStream 事件过滤 ===");

        CountDownLatch latch = new CountDownLatch(1);
        jdk.jfr.consumer.RecordingStream recordingStream = new jdk.jfr.consumer.RecordingStream();

        // 设置最大保留时间
        recordingStream.setMaxAge(java.time.Duration.ofSeconds(10));
        System.out.println("设置最大事件保留时间: 10 秒");

        // 设置最大大小
        recordingStream.setMaxSize(100 * 1024 * 1024L);
        System.out.println("设置最大事件保留大小: 100 MB");

        // 只订阅文件读写事件
        recordingStream.onEvent("jdk.FileRead", event -> {
            System.out.println("收到 FileRead 事件:");
            System.out.println("  文件路径: " + event.getValue("path"));
            System.out.println("  读取字节数: " + event.getValue("bytesRead"));
            System.out.println("  结束时间: " + event.getEndTime());
        });

        recordingStream.onEvent("jdk.FileWrite", event -> {
            System.out.println("收到 FileWrite 事件:");
            System.out.println("  文件路径: " + event.getValue("path"));
            System.out.println("  写入字节数: " + event.getValue("bytesWritten"));
        });

        // 订阅 GC 事件用于确认流正常工作
        recordingStream.onEvent("jdk.GarbageCollection", event -> {
            System.out.println("收到 GC 事件 (过滤测试):");
            System.out.println("  持续时间: " + event.getDuration());
            latch.countDown();
        });

        // 启动流
        recordingStream.startAsync();

        // 触发 GC
        System.gc();

        // 等待事件
        boolean eventReceived = latch.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        System.out.println("过滤测试事件接收状态: " + (eventReceived ? "成功" : "超时"));

        recordingStream.close();
        System.out.println("RecordingStream 已关闭");
    }

    /**
     * 测试 JFR 事件流的基本配置和生命周期
     *
     * 演示 RecordingStream 的配置选项以及启用/关闭的基本操作。
     */
    @Test
    public void testRecordingStreamConfiguration() throws Exception {
        System.out.println("=== 测试 RecordingStream 基本配置 ===");
        System.out.println("JFR 事件流 API 的关键特性:");
        System.out.println("1. 支持实时订阅 JFR 事件，无需 JMC 工具");
        System.out.println("2. 支持 onEvent() 按事件类型名称订阅");
        System.out.println("3. 支持 setMaxAge() 控制事件保留时间");
        System.out.println("4. 支持 setMaxSize() 控制事件保留大小");
        System.out.println("5. 支持 startAsync() 异步启动流");
        System.out.println("6. 支持 start() 同步启动（阻塞当前线程）");
        System.out.println("7. 支持 close() 关闭流释放资源");
        System.out.println();

        System.out.println("JFR 事件流典型使用场景:");
        System.out.println("- 性能监控: 实时监控 GC 事件、CPU 负载");
        System.out.println("- 内存分析: 监控线程分配统计、对象分配");
        System.out.println("- I/O 监控: 跟踪文件读写、网络操作");
        System.out.println("- 异常监控: 捕获异常事件、线程阻塞事件");
        System.out.println();
        System.out.println("注意: 在实际应用中，JFR 事件流通常用于生产环境监控，");
        System.out.println("需要确保 JFR 功能已启用（-XX:+FlightRecorder）");
    }
}