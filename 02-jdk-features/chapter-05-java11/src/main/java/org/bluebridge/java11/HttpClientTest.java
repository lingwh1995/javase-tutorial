package org.bluebridge.java11;

import org.junit.Test;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Java11 HttpClient 测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HttpClientTest {

    /**
     * 使用 HttpClient 发送get请求
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testHttpClientGet() throws IOException, InterruptedException {
        // ①、创建HttpClient对象
        HttpClient client = HttpClient.newBuilder()// 1
            // 指定HTTP协议的版本
            .version(HttpClient.Version.HTTP_2)
            // 指定重定向策略
            .followRedirects(HttpClient.Redirect.NORMAL)
            // 指定超时的时长
            .connectTimeout(Duration.ofSeconds(20))
            // 如有必要，可通过该方法指定代理服务器地址
            // .proxy(ProxySelector.of(new InetSocketAddress("proxy.crazyit.com", 80)))
            .build();
        // ②、创建HttpRequest对象
        HttpRequest request = HttpRequest.newBuilder()// 2
            // 执行请求的URL
            .uri(URI.create("https://baike.baidu.com/item/%E7%AB%A5%E7%8E%B2/974254?fr=aladdin"))
            // 指定请求超时的时长
            .timeout(Duration.ofMinutes(2))
            // 指定请求头
            .header("Content-Type", "text/html")
            // 创建GET请求
            .GET()
            .build();
        // HttpResponse.BodyHandlers.ofString()指定将服务器响应转化成字符串
        HttpResponse.BodyHandler<String> bh = HttpResponse.BodyHandlers.ofString();
        // ③、发送请求，获取服务器响应
        HttpResponse<String> response = client.send(request, bh); // 3
        // 获取服务器响应的状态码
        System.out.println("响应的状态码:" + response.statusCode());
        System.out.println("响应头:\n" + response.headers());
        System.out.println("响应体:" + response.body());
    }

    /**
     * 使用 HttpClient 发送post请求
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testHttpClientPost() throws IOException, InterruptedException {
        // 为CookieHandler设置默认的Cookie管理器
        CookieHandler.setDefault(new CookieManager());
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            // 设置默认的Cookie处理器
            .cookieHandler(CookieHandler.getDefault())// 1
            .build();
        // 创建发送POST请求的request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8888/foo/login.jsp"))
            .timeout(Duration.ofMinutes(2))
            // 指定以提交表单的方式编码请求体
            .header("Content-Type", "application/x-www-form-urlencoded")
            // 通过字符串创建请求体，然后作为POST请求的请求参数
            .POST(HttpRequest.BodyPublishers.ofString("name=crazyit.org&pass=leegang"))// 2
            .build();
        // HttpResponse.BodyHandlers.ofString()指定将服务器响应转化成字符串
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST请求的响应码:" + response.statusCode());
        System.out.println("POST请求的响应体:" + response.body());
        // 创建发送GET请求的request
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8888/foo/secret.jsp"))
            .timeout(Duration.ofMinutes(2))
            .header("Content-Type", "text/html")
            .GET()
            .build();
        // HttpResponse.BodyHandlers.ofString()指定将服务器响应转化成字符串
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET请求的响应码:" + response.statusCode());
        System.out.println("GET请求的响应体:" + response.body());
    }
}
