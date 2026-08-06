package org.bluebridge.section_11_jdk11_lts.unit_03_http_client;

import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * JDK 11 HttpClient 测试（从 JDK 9 孵化器转正）
 *
 * @author lingwh
 * @date 2026/08/05 19:12
 */
public class HttpClientTest {

    /**
     * 测试 HttpClient 发送同步 GET 请求
     * 使用 HttpClient.newHttpClient() 发送同步请求并获取响应
     */
    @Test
    public void testSyncGet() throws Exception {
        // 创建 HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // 构建 GET 请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://httpbin.org/get"))
                .GET()
                .build();

        // 同步发送请求
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("同步 GET 响应状态码: " + response.statusCode());
        System.out.println("同步 GET 响应体: " + response.body());
    }

    /**
     * 测试 HttpClient 发送异步 GET 请求
     * 使用 sendAsync() 方法异步发送请求，返回 CompletableFuture
     */
    @Test
    public void testAsyncGet() throws Exception {
        // 创建 HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // 构建 GET 请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://httpbin.org/get"))
                .GET()
                .build();

        // 异步发送请求
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString());

        // 异步获取结果
        HttpResponse<String> response = future.get();
        System.out.println("异步 GET 响应状态码: " + response.statusCode());
        System.out.println("异步 GET 响应体: " + response.body());
    }

    /**
     * 测试 HttpClient 发送 POST 请求
     * 使用 HttpRequest.BodyPublishers.ofString() 发送请求体
     */
    @Test
    public void testPostRequest() throws Exception {
        // 创建 HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // 构建 POST 请求，携带请求体
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"JDK11\"}"))
                .build();

        // 同步发送请求
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST 响应状态码: " + response.statusCode());
        System.out.println("POST 响应体: " + response.body());
    }

    /**
     * 测试 HttpClient 设置超时和自定义配置
     * 使用 HttpClient.newBuilder() 配置超时、重定向策略等
     */
    @Test
    public void testHttpClientWithTimeout() throws Exception {
        // 创建带超时配置的 HttpClient
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        // 构建 GET 请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://httpbin.org/get"))
                .timeout(java.time.Duration.ofSeconds(5))
                .GET()
                .build();

        // 发送请求
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("自定义配置 GET 响应状态码: " + response.statusCode());
        System.out.println("自定义配置 GET 响应体: " + response.body());
    }
}