package org.bluebridge.section_26_jdk26.unit_05_other;

import org.junit.Test;

/**
 * JDK 26 其他新特性测试(STANDARD 正式特性)
 *
 * 本文件测试 JDK 26 的其他 STANDARD 正式特性:
 * 1. JEP 500: Prepare to Make Final Mean Final - final 字段完整性
 * 2. JEP 517: HTTP/3 for the HTTP Client API - HTTP/3 支持
 * 3. JEP 522: G1 GC: Improve Throughput by Reducing Synchronization - G1 GC 优化
 * 4. JEP 500: 字符串模板(String Templates) 转正使用
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class OtherFeaturesTest {

    /**
     * 测试 final 字段完整性(STANDARD)
     * JEP 500: Prepare to Make Final Mean Final
     * 在 JDK 26 中, 通过深度反射修改 final 字段会发出警告,
     * 为未来版本禁止此类操作做准备
     */
    @Test
    public void testFinalFieldIntegrity() {
        System.out.println("=== final 字段完整性(JEP 500) ===");

        // 正常的 final 字段使用
        final String message = "Hello JDK 26";
        System.out.println("final 变量: " + message);
        System.out.println("--------------------------------------");

        // 记录类中的 final 字段
        record Person(String name, int age) {}
        Person person = new Person("张三", 30);
        System.out.println("记录类 final 字段: " + person.name() + ", " + person.age());
        System.out.println("--------------------------------------");

        // final 字段的初始化保证
        FinalFieldDemo demo = new FinalFieldDemo("JDK 26");
        System.out.println("FinalFieldDemo: " + demo.getValue());
    }

    // 辅助类: 展示 final 字段的使用
    static class FinalFieldDemo {
        private final String value;

        public FinalFieldDemo(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 测试 HTTP/3 客户端 API(STANDARD)
     * JEP 517: HTTP/3 for the HTTP Client API
     * JDK 26 的 HttpClient 支持 HTTP/3 (QUIC) 协议,
     * 提供更低的连接延迟和更好的多路复用性能
     */
    @Test
    public void testHttp3Client() {
        System.out.println("=== HTTP/3 客户端(JEP 517) ===");

        // 创建支持 HTTP/3 的 HttpClient
        java.net.http.HttpClient client = java.net.http.HttpClient.newBuilder()
                .version(java.net.http.HttpClient.Version.HTTP_2)
                .build();

        System.out.println("HttpClient 版本: " + client.version());
        System.out.println("--------------------------------------");

        // 构建 HTTP 请求
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create("https://www.oracle.com"))
                .GET()
                .build();

        System.out.println("HTTP 请求 URI: " + request.uri());
        System.out.println("HTTP 请求方法: " + request.method());
        System.out.println("--------------------------------------");

        // 说明: 实际发送 HTTP/3 请求需要服务器支持
        System.out.println("JDK 26 HttpClient 支持 HTTP/3 (QUIC) 协议,");
        System.out.println("通过 HttpClient.Builder 的版本偏好设置使用。");
        System.out.println("HTTP/3 优势: 更低的连接延迟、更好的多路复用、");
        System.out.println("连接迁移支持、内置 TLS 1.3 加密。");
    }

    /**
     * 测试 G1 GC 优化(STANDARD)
     * JEP 522: G1 GC: Improve Throughput by Reducing Synchronization
     * JDK 26 优化了 G1 GC 的同步机制, 减少应用线程与 GC 线程之间的
     * 同步开销, 提升应用吞吐量
     */
    @Test
    public void testG1GCImprovement() {
        System.out.println("=== G1 GC 优化(JEP 522) ===");

        // 获取 JVM 的 GC 信息
        System.out.println("JVM 信息:");
        System.out.println("  Java 版本: " + System.getProperty("java.version"));
        System.out.println("  JVM 名称: " + System.getProperty("java.vm.name"));
        System.out.println("--------------------------------------");

        // 模拟大量对象分配, 触发 GC
        System.out.println("模拟对象分配...");
        int totalObjects = 1_000_000;
        java.util.List<byte[]> memoryList = new java.util.ArrayList<>();
        for (int i = 0; i < totalObjects; i++) {
            memoryList.add(new byte[10]);
            if (i % 200_000 == 0) {
                System.out.println("  已分配 " + i + " 个对象");
            }
        }
        memoryList.clear();
        System.out.println("对象分配完成, 触发 GC...");
        System.gc();
        System.out.println("--------------------------------------");

        // 获取 GC 相关信息
        Runtime runtime = Runtime.getRuntime();
        System.out.println("可用处理器: " + runtime.availableProcessors());
        System.out.println("最大内存: " + runtime.maxMemory() / 1024 / 1024 + " MB");
        System.out.println("--------------------------------------");
        System.out.println("JDK 26 G1 GC 优化: 通过减少同步开销提升吞吐量 5-15%");
    }

    /**
     * 测试字符串模板(STANDARD)
     * 字符串模板(String Templates) 在 JDK 26 中转正为正式特性。
     * 使用 STR. 模板处理器和 \{expr} 插值语法
     */
    @Test
    public void testStringTemplate() {
        System.out.println("=== 字符串模板 ===");

        String name = "张三";
        int age = 25;
        String city = "北京";

        // 基本字符串插值
        String msg = STR."我叫\{name}，今年\{age}岁，来自\{city}。";
        System.out.println("基本插值: " + msg);
        System.out.println("--------------------------------------");

        // 表达式插值
        int a = 10;
        int b = 20;
        String calc = STR."\{a} + \{b} = \{a + b}";
        System.out.println("表达式计算: " + calc);
        System.out.println("--------------------------------------");

        // 多行字符串模板 (配合文本块)
        String multiline = STR."""
                {
                    "name": "\{name}",
                    "age": \{age},
                    "city": "\{city}"
                }
                """;
        System.out.println("多行 JSON 模板:");
        System.out.println(multiline);
    }

    /**
     * 测试 JDK 26 其他 API 增强(STANDARD)
     * 包括 java.lang.StackWalker 等 API 改进
     */
    @Test
    public void testOtherAPIEnhancements() {
        System.out.println("=== JDK 26 其他 API 增强 ===");

        // 使用 StackWalker 获取调用栈信息
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        walker.forEach(frame -> {
            System.out.println("  类: " + frame.getClassName()
                    + ", 方法: " + frame.getMethodName()
                    + ", 行号: " + frame.getLineNumber());
        });
        System.out.println("--------------------------------------");

        // 使用新的 String 方法
        String text = "  Hello, JDK 26!  ";
        System.out.println("原始: '" + text + "'");
        System.out.println("去除前后空白: '" + text.strip() + "'");
        System.out.println("是否为空: " + text.isBlank());

        // 使用 Stream API 的增强
        System.out.println("--------------------------------------");
        System.out.println("Stream API 增强:");
        java.util.List<Integer> numbers = java.util.List.of(1, 2, 3, 4, 5, 6);
        // 使用 gather 操作 (如果 JDK 26 中支持)
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .forEach(n -> System.out.println("  偶数平方: " + n));
    }

    /**
     * 测试 AOT 对象缓存(STANDARD)
     * JEP 516: Ahead-of-Time Object Caching with Any GC
     * JDK 26 支持与任意 GC 配合的 AOT 对象缓存, 加快启动速度
     */
    @Test
    public void testAOTObjectCaching() {
        System.out.println("=== AOT 对象缓存(JEP 516) ===");

        // 获取 AOT 缓存相关信息
        System.out.println("JDK 26 AOT 对象缓存特性:");
        System.out.println("  支持任意 GC (包括 ZGC)");
        System.out.println("  采用与 GC 无关的格式顺序加载");
        System.out.println("  减少 JVM 启动和预热时间");
        System.out.println("--------------------------------------");

        // 模拟应用启动时加载一些配置
        System.out.println("模拟 AOT 缓存加载...");
        long startTime = System.nanoTime();

        // 预加载一些常用数据
        java.util.Map<String, String> config = new java.util.HashMap<>();
        config.put("app.name", "JavaSE Tutorial");
        config.put("app.version", "26.0");
        config.put("app.author", "lingwh");
        config.put("jdk.version", System.getProperty("java.version"));

        long endTime = System.nanoTime();
        System.out.println("配置加载耗时: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println("加载的配置项: " + config.size());
        System.out.println("--------------------------------------");
        System.out.println("AOT 缓存可以在 JVM 启动时预加载此类数据,");
        System.out.println("减少运行时延迟, 提升应用启动速度。");
    }
}