﻿package org.bluebridge.section_11_jdk11.unit_03_httpclient;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Java11 HttpClient 测试
 *
 * Java11 正式将 HttpClient 标准化, 位于 java.net.http 包下, 支持 HTTP/1.1 和 HTTP/2 协议,
 * 提供了同步 send() 和异步 sendAsync() 两种请求方式, 核心 API 如下:
 * 1. HttpClient: 发送请求的客户端
 * 2. HttpRequest: 封装请求信息
 * 3. HttpResponse: 封装响应信息
 *
 * @author lingwh
 * @date 2026/08/05 18:23
 */
public class HttpClientTest {

    /**
     * 使用 HttpClient 发送同步 GET 请求
     */
    @Test
    public void testHttpClientSendGet() throws IOException, InterruptedException {
        // 1. 创建 HttpClient 对象
        HttpClient client = HttpClient.newBuilder()
                // 指定 HTTP 协议的版本
                .version(HttpClient.Version.HTTP_2)
                // 指定重定向策略
                .followRedirects(HttpClient.Redirect.NORMAL)
                // 指定连接超时时长
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        // 2. 创建 HttpRequest 对象
        HttpRequest request = HttpRequest.newBuilder()
                // 指定请求的 URL
                .uri(URI.create("https://www.baidu.com"))
                // 指定请求超时时长
                .timeout(Duration.ofSeconds(30))
                // 指定请求头
                .header("User-Agent", "Java11 HttpClient")
                // 指定请求方式为 GET
                .GET()
                .build();
        // 3. 发送请求, 获取响应
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // 输出响应信息
        System.out.println("响应状态码: " + response.statusCode());
        System.out.println("响应头: " + response.headers().map());
        String body = response.body();
        System.out.println("响应体(前200个字符): " + body.substring(0, Math.min(200, body.length())));
    }

    /**
     * 使用 HttpClient 发送 POST 请求, 请求体为 JSON 字符串
     */
    @Test
    public void testHttpClientSendPost() throws IOException, InterruptedException {
        // 使用便捷方法创建默认配置的 HttpClient 对象
        HttpClient client = HttpClient.newHttpClient();
        // 创建 POST 请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .timeout(Duration.ofSeconds(30))
                // 指定请求头
                .header("Content-Type", "application/json")
                // 通过 BodyPublishers.ofString() 创建请求体, 并指定请求方式为 POST
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\": \"bluebridge\"}"))
                .build();
        // 发送请求, 获取响应
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("响应状态码: " + response.statusCode());
        System.out.println("响应体: " + response.body());
    }

    /**
     * 使用 HttpClient 发送异步 GET 请求, sendAsync() 返回 CompletableFuture, 不会阻塞当前线程
     */
    @Test
    public void testHttpClientSendAsync() throws ExecutionException, InterruptedException {
        // 创建 HttpClient 对象
        HttpClient client = HttpClient.newHttpClient();
        // 创建 HttpRequest 对象
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.baidu.com"))
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();
        // 异步发送请求, 不会阻塞当前线程
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        // 获取异步请求的响应结果
        HttpResponse<String> response = future.get();
        System.out.println("异步请求响应状态码: " + response.statusCode());
    }
}
