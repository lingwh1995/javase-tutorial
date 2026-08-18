package org.bluebridge.section_18_jdk18.unit_01_webserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * JDK 18 Simple Web Server (JEP 408) 测试
 * 测试 SimpleFileServer 和 HttpServer 的基本用法
 *
 * 演化历程: Simple Web Server JDK 18 STANDARD（JEP 408）
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class SimpleWebServerTest {

    /**
     * 测试使用 SimpleFileServer 创建静态文件服务器
     * SimpleFileServer 是 JDK 18 引入的简易文件服务器
     */
    @Test
    public void testSimpleFileServer() throws IOException {
        // 使用 SimpleFileServer 创建文件服务器，指定根目录和端口
        HttpServer fileServer = SimpleFileServer.createFileServer(
                new InetSocketAddress(8081),
                Path.of("."),
                SimpleFileServer.OutputLevel.VERBOSE
        );
        fileServer.start();
        System.out.println("SimpleFileServer 已启动，监听端口: 8081");
        // 立即关闭服务器
        fileServer.stop(0);
        System.out.println("SimpleFileServer 已关闭");
    }

    /**
     * 测试使用 HttpServer 创建自定义 HTTP 服务器
     * 注册自定义的 HttpHandler 处理请求
     */
    @Test
    public void testHttpServerWithHandler() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
        // 注册自定义处理器
        server.createContext("/hello", new HelloHandler());
        server.createContext("/echo", exchange -> {
            String response = "Echo: " + exchange.getRequestURI().toString();
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        });
        server.start();
        System.out.println("自定义 HttpServer 已启动，监听端口: 8082");
        // 立即关闭服务器
        server.stop(0);
        System.out.println("自定义 HttpServer 已关闭");
    }

    /**
     * 测试 HttpHandler 的多种实现方式
     * 分别使用匿名内部类和 Lambda 表达式创建处理器
     */
    @Test
    public void testMultipleHandlers() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8083), 0);

        // 使用匿名内部类创建处理器
        server.createContext("/api/status", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "{\"status\": \"OK\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                }
            }
        });

        // 使用 Lambda 表达式创建处理器
        server.createContext("/api/version", exchange -> {
            String response = "JDK 18 Simple Web Server";
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        });

        server.start();
        System.out.println("多处理器服务器已启动，监听端口: 8083");
        server.stop(0);
        System.out.println("多处理器服务器已关闭");
    }

    /**
     * 自定义 HttpHandler 实现类
     * 处理 /hello 路径的请求，返回问候信息
     */
    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello from JDK 18 Simple Web Server!";
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}