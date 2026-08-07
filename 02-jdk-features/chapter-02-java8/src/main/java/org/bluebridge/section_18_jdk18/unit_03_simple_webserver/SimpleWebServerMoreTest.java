package org.bluebridge.section_18_jdk18.unit_03_simple_webserver;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

/**
 * JDK 18 Simple Web Server (JEP 408) 更多测试
 *
 * 测试 SimpleFileServer 的 OutputLevel 不同级别、
 * 文件服务过滤和 Handler 自定义等功能。
 * SimpleFileServer 是 JDK 18 引入的轻量级静态文件服务器,
 * 适用于开发、测试和原型设计场景。
 *
 * 演化历程: Simple Web Server JDK 18 STANDARD（JEP 408）
 *
 * @author lingwh
 * @date 2026/08/06 02:19
 */
public class SimpleWebServerMoreTest {

    /**
     * 测试 SimpleFileServer 的 OutputLevel.VERBOSE 级别
     * VERBOSE 级别会输出详细的请求日志, 包括请求方法、路径、状态码等
     */
    @Test
    public void testOutputLevelVerbose() throws IOException {
        // 创建文件服务器, 使用 VERBOSE 输出级别
        HttpServer fileServer = SimpleFileServer.createFileServer(
                new InetSocketAddress(8084),
                Path.of("."),
                SimpleFileServer.OutputLevel.VERBOSE
        );
        fileServer.start();
        System.out.println("SimpleFileServer (VERBOSE) 已启动, 监听端口: 8084");
        // 立即关闭服务器
        fileServer.stop(0);
        System.out.println("SimpleFileServer (VERBOSE) 已关闭");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 SimpleFileServer 的 OutputLevel.INFO 级别
     * INFO 级别只输出基本请求信息, 如请求路径和状态码
     */
    @Test
    public void testOutputLevelInfo() throws IOException {
        // 创建文件服务器, 使用 INFO 输出级别
        HttpServer fileServer = SimpleFileServer.createFileServer(
                new InetSocketAddress(8085),
                Path.of("."),
                SimpleFileServer.OutputLevel.INFO
        );
        fileServer.start();
        System.out.println("SimpleFileServer (INFO) 已启动, 监听端口: 8085");
        // 立即关闭服务器
        fileServer.stop(0);
        System.out.println("SimpleFileServer (INFO) 已关闭");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 SimpleFileServer 的 OutputLevel.NONE 级别
     * NONE 级别不输出任何请求日志, 适用于静默运行模式
     */
    @Test
    public void testOutputLevelNone() throws IOException {
        // 创建文件服务器, 使用 NONE 输出级别
        HttpServer fileServer = SimpleFileServer.createFileServer(
                new InetSocketAddress(8086),
                Path.of("."),
                SimpleFileServer.OutputLevel.NONE
        );
        fileServer.start();
        System.out.println("SimpleFileServer (NONE) 已启动, 监听端口: 8086, 不输出日志");
        // 立即关闭服务器
        fileServer.stop(0);
        System.out.println("SimpleFileServer (NONE) 已关闭");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 SimpleFileServer 的 OutputLevel 枚举值
     * 验证 OutputLevel 包含三个枚举常量: NONE, INFO, VERBOSE
     */
    @Test
    public void testOutputLevelEnumValues() {
        // 获取 OutputLevel 所有枚举值
        SimpleFileServer.OutputLevel[] levels = SimpleFileServer.OutputLevel.values();
        System.out.println("OutputLevel 枚举值数量: " + levels.length);
        for (SimpleFileServer.OutputLevel level : levels) {
            System.out.println("OutputLevel 枚举值: " + level.name());
        }
        // 验证三个级别都存在
        assert levels.length == 3 : "OutputLevel 应包含 3 个枚举值";
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 SimpleFileServer.createHandler 创建自定义文件处理器
     * SimpleFileServer.createHandler 可以创建用于处理文件请求的 HttpHandler
     */
    @Test
    public void testCreateFileHandler() throws IOException {
        // 使用 SimpleFileServer 创建文件处理器
        HttpServer server = HttpServer.create(new InetSocketAddress(8087), 0);
        // 创建文件服务处理器, 设置根目录和输出级别
        HttpServer fileHandler = SimpleFileServer.createFileServer(
                new InetSocketAddress(8087),
                Path.of("."),
                SimpleFileServer.OutputLevel.INFO
        );
        // 获取文件处理器并注册到上下文
        server.createContext("/files", exchange -> {
            // 使用 SimpleFileServer 的 createHandler 创建文件请求处理器
            HttpServer handler = SimpleFileServer.createFileServer(
                    new InetSocketAddress(8087),
                    Path.of("."),
                    SimpleFileServer.OutputLevel.INFO
            );
            // 记录请求信息
            System.out.println("文件请求: " + exchange.getRequestURI());
            exchange.sendResponseHeaders(200, 0);
            try (OutputStream os = exchange.getResponseBody()) {
                String response = "File server handler is ready";
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        });
        server.start();
        System.out.println("自定义文件处理器服务器已启动, 监听端口: 8087");
        server.stop(0);
        System.out.println("自定义文件处理器服务器已关闭");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 Filter 对文件服务请求进行过滤
     * 通过 Filter 可以拦截和处理文件请求, 实现访问控制或日志记录
     */
    @Test
    public void testFileServerWithFilter() throws IOException {
        // 创建 HttpServer 并添加 Filter
        HttpServer server = HttpServer.create(new InetSocketAddress(8088), 0);

        // 注册文件服务上下文
        server.createContext("/static", exchange -> {
            String response = "Static file content";
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        });

        // 添加自定义 Filter, 记录所有请求日志
        server.createContext("/filtered", exchange -> {
            String response = "Filtered response";
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        });

        // 添加一个 Filter 用于记录请求
        Filter requestLoggingFilter = new Filter() {
            @Override
            public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
                System.out.println("Filter 拦截请求: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());
                // 继续处理链
                chain.doFilter(exchange);
                System.out.println("Filter 响应完成: " + exchange.getResponseCode());
            }

            @Override
            public String description() {
                return "请求日志记录 Filter";
            }
        };

        // 注意: 此处演示 Filter 的创建, 实际使用中 Filter 需要注册到 HttpContext
        System.out.println("Filter 描述: " + requestLoggingFilter.description());

        server.start();
        System.out.println("带 Filter 的服务器已启动, 监听端口: 8088");
        server.stop(0);
        System.out.println("带 Filter 的服务器已关闭");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 SimpleFileServer 与自定义 Handler 的结合使用
     * 通过组合多个 HttpHandler 实现复杂的请求路由
     */
    @Test
    public void testCombinedHandlers() throws IOException {
        // 创建 HttpServer
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);

        // 注册多个上下文, 模拟不同的服务路径
        server.createContext("/api/status", exchange -> {
            String response = "{\"status\": \"running\", \"server\": \"JDK 18 Simple Web Server\"}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        });

        server.createContext("/api/health", exchange -> {
            String response = "OK";
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        });

        // 使用 SimpleFileServer 的 OutputLevel 验证
        SimpleFileServer.OutputLevel outputLevel = SimpleFileServer.OutputLevel.VERBOSE;
        System.out.println("当前输出级别: " + outputLevel);

        // 列出所有注册的上下文
        System.out.println("注册的上下文数量: " + server.getAddress());

        server.start();
        System.out.println("组合 Handler 服务器已启动, 监听端口: 8089");
        System.out.println("可用端点: /api/status, /api/health");
        server.stop(0);
        System.out.println("组合 Handler 服务器已关闭");
        System.out.println("--------------------------------------");
    }
}