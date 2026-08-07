package org.bluebridge.section_06_jdk6.unit_07_httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * JDK 6 轻量级 HTTP Server 测试
 *
 * 演化历程：
 *   JDK 6 在 com.sun.net.httpserver 包中引入了轻量级 HTTP 服务器，
 *   允许 Java 程序直接创建 HTTP 服务器，无需依赖外部 Web 容器或框架。
 *   适用于开发测试、嵌入式系统、轻量级 REST 服务等场景。
 *
 * 核心类：
 *   - HttpServer：HTTP 服务器的主类，负责监听端口和分发请求
 *   - HttpHandler：请求处理器接口，处理具体的 HTTP 请求
 *   - HttpExchange：封装 HTTP 请求和响应的对象
 *   - HttpContext：将 URL 路径映射到对应的 HttpHandler
 *
 * @author lingwh
 * @date 2026/08/06 18:19
 */
public class HttpServerTest {

    /**
     * 测试创建 HttpServer 实例并配置基本属性
     * 包括绑定地址、端口、backlog 等
     */
    @Test
    public void testCreateHttpServer() throws IOException {
        // 创建 HttpServer 实例，绑定到本地 0 端口（自动分配可用端口）
        InetSocketAddress address = new InetSocketAddress("localhost", 0);
        HttpServer server = HttpServer.create(address, 0);

        System.out.println("HttpServer 创建成功");
        System.out.println("服务器地址：" + server.getAddress());
        System.out.println("绑定地址：" + server.getAddress().getHostString());
        System.out.println("绑定端口：" + server.getAddress().getPort());

        // 设置线程池（可选）
        server.setExecutor(Executors.newFixedThreadPool(2));
        System.out.println("线程池设置完成：固定线程池大小=2");

        // 停止服务器
        server.stop(0);
        System.out.println("服务器已停止");
    }

    /**
     * 测试 HttpServer 的上下文（Context）管理
     * 演示创建、获取和删除上下文
     */
    @Test
    public void testHttpServerContext() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);

        // 创建上下文：将路径 /hello 映射到处理器
        server.createContext("/hello", exchange -> {
            String response = "Hello World!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        System.out.println("创建上下文：/hello");

        // 创建另一个上下文
        server.createContext("/api", exchange -> {
            String response = "{\"status\":\"ok\"}";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        System.out.println("创建上下文：/api");

        // 获取所有已注册的上下文
        List<com.sun.net.httpserver.HttpContext> contexts = server.getContexts();
        System.out.println("已注册的上下文数量：" + contexts.size());
        for (com.sun.net.httpserver.HttpContext context : contexts) {
            System.out.println("  路径：" + context.getPath());
        }

        // 删除上下文
        server.removeContext("/api");
        System.out.println("删除上下文：/api 后，剩余上下文数量：" + server.getContexts().size());

        server.stop(0);
    }

    /**
     * 测试启动和停止 HTTP 服务器
     * 使用 CountDownLatch 模拟运行一段时间后停止
     */
    @Test
    public void testStartAndStopServer() throws IOException, InterruptedException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);

        // 注册一个简单的处理器
        server.createContext("/test", exchange -> {
            String response = "Server is running!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        // 启动服务器（默认不启动，需要显式调用 start()）
        server.start();
        System.out.println("HttpServer 已启动，监听端口：" + server.getAddress().getPort());
        System.out.println("服务器状态：运行中");

        // 模拟服务器运行一段时间
        TimeUnit.MILLISECONDS.sleep(100);

        // 停止服务器
        server.stop(0);
        System.out.println("HttpServer 已停止，延迟参数：0（立即停止）");
    }

    /**
     * 测试 HttpExchange 请求信息获取
     * 包括请求方法、URI、头部、请求体等
     */
    @Test
    public void testHttpExchangeRequestInfo() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);

        // 注册一个处理器，展示请求信息
        server.createContext("/info", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // 获取请求信息
                String requestMethod = exchange.getRequestMethod();
                URI requestURI = exchange.getRequestURI();
                Headers requestHeaders = exchange.getRequestHeaders();

                System.out.println("=== 请求信息 ===");
                System.out.println("请求方法：" + requestMethod);
                System.out.println("请求 URI：" + requestURI);
                System.out.println("协议版本：" + exchange.getProtocol());
                System.out.println("请求头信息：");
                for (Map.Entry<String, List<String>> header : requestHeaders.entrySet()) {
                    System.out.println("  " + header.getKey() + ": " + String.join(", ", header.getValue()));
                }

                // 发送响应
                String response = "Request info logged.";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        server.start();
        System.out.println("服务器已启动，访问 /info 路径可查看请求信息");

        // 模拟请求处理
        TimeUnit.MILLISECONDS.sleep(50);

        server.stop(0);
        System.out.println("服务器已停止");
    }

    /**
     * 测试 HttpExchange 响应设置
     * 包括设置响应头、状态码、响应体等
     */
    @Test
    public void testHttpExchangeResponse() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);

        server.createContext("/response", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // 设置响应头
                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "text/plain; charset=UTF-8");
                responseHeaders.set("X-Custom-Header", "CustomValue");
                responseHeaders.set("Server", "Java-HttpServer/1.0");

                // 设置响应状态码和响应体
                String responseBody = "响应测试成功！\n"
                        + "状态码：200 OK\n"
                        + "内容类型：text/plain; charset=UTF-8\n"
                        + "自定义头：X-Custom-Header = CustomValue";

                exchange.sendResponseHeaders(200, responseBody.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(responseBody.getBytes());
                }

                System.out.println("响应已发送：");
                System.out.println(responseBody);
            }
        });

        server.start();
        TimeUnit.MILLISECONDS.sleep(50);
        server.stop(0);
    }

    /**
     * 测试 HTTP 错误响应
     * 演示发送 404、500 等错误状态码
     */
    @Test
    public void testHttpErrorResponse() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);

        server.createContext("/error", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // 模拟根据请求参数返回不同错误
                String query = exchange.getRequestURI().getQuery();
                String response;

                if (query != null && query.contains("type=404")) {
                    // 返回 404 Not Found
                    response = "404 - 资源未找到";
                    exchange.sendResponseHeaders(404, response.getBytes().length);
                } else if (query != null && query.contains("type=500")) {
                    // 返回 500 Internal Server Error
                    response = "500 - 服务器内部错误";
                    exchange.sendResponseHeaders(500, response.getBytes().length);
                } else {
                    // 返回 400 Bad Request
                    response = "400 - 错误的请求参数";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                }

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }

                System.out.println("错误响应已发送：" + response);
            }
        });

        server.start();
        System.out.println("服务器已启动，访问 /error 可测试不同错误响应");
        System.out.println("  /error?type=404 -> 404 Not Found");
        System.out.println("  /error?type=500 -> 500 Internal Server Error");
        System.out.println("  /error          -> 400 Bad Request");

        TimeUnit.MILLISECONDS.sleep(50);
        server.stop(0);
    }

    /**
     * 测试使用自定义线程池
     * HttpServer.setExecutor() 允许设置自定义线程池处理请求
     */
    @Test
    public void testCustomExecutor() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);

        // 设置自定义线程池：固定大小 3 的线程池
        server.setExecutor(Executors.newFixedThreadPool(3));
        System.out.println("自定义线程池设置完成：FixedThreadPool(3)");

        server.createContext("/executor", exchange -> {
            String threadName = Thread.currentThread().getName();
            String response = "处理线程：" + threadName;
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
            System.out.println("请求由线程 " + threadName + " 处理");
        });

        server.start();
        System.out.println("服务器已启动，请求将由自定义线程池中的线程处理");

        TimeUnit.MILLISECONDS.sleep(50);
        server.stop(0);
    }

    /**
     * 测试 HttpServer 的完整生命周期
     * 创建 -> 配置上下文 -> 设置线程池 -> 启动 -> 处理请求 -> 停止
     */
    @Test
    public void testServerLifecycle() throws IOException, InterruptedException {
        System.out.println("=== HttpServer 完整生命周期演示 ===");

        // 1. 创建
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 5);
        System.out.println("1. 创建 HttpServer：端口=" + server.getAddress().getPort());

        // 2. 配置上下文
        server.createContext("/", exchange -> {
            String response = "根路径";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        server.createContext("/health", exchange -> {
            String response = "OK";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        System.out.println("2. 配置上下文：/ 和 /health");

        // 3. 设置线程池
        server.setExecutor(Executors.newCachedThreadPool());
        System.out.println("3. 设置线程池：CachedThreadPool");

        // 4. 启动
        server.start();
        System.out.println("4. 启动服务器");

        // 5. 运行中
        System.out.println("5. 服务器运行中...");
        System.out.println("   上下文数量：" + server.getContexts().size());
        TimeUnit.MILLISECONDS.sleep(100);

        // 6. 停止
        server.stop(1); // 延迟 1 秒停止
        System.out.println("6. 停止服务器（延迟 1 秒）");
        System.out.println("=== 生命周期演示完成 ===");
    }

    /**
     * 测试 HttpServer 的配置属性
     * 包括 backlog 设置、地址绑定等
     */
    @Test
    public void testServerConfiguration() throws IOException {
        System.out.println("=== HttpServer 配置属性说明 ===");

        // backlog 参数说明
        System.out.println("HttpServer.create() 参数说明：");
        System.out.println("  - addr：绑定地址和端口，port=0 表示自动分配可用端口");
        System.out.println("  - backlog：请求队列最大长度，0 表示使用系统默认值");

        // 创建不同配置的服务器
        HttpServer server1 = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        System.out.println("服务器1：backlog=0（默认），端口=" + server1.getAddress().getPort());

        HttpServer server2 = HttpServer.create(new InetSocketAddress("localhost", 0), 100);
        System.out.println("服务器2：backlog=100，端口=" + server2.getAddress().getPort());

        server1.stop(0);
        server2.stop(0);

        System.out.println();
        System.out.println("HttpServer 默认配置：");
        System.out.println("  - 支持 HTTP/1.1 协议");
        System.out.println("  - 默认不设置线程池（每个请求在新线程中处理）");
        System.out.println("  - 默认 executor 为 null");
        System.out.println("  - 最大连接数受操作系统限制");
    }
}