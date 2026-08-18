package org.bluebridge.section_13_jdk13.unit_02_socket;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * JDK 13 Socket API 重实现测试（JEP 353）
 *
 * JDK 13 重新实现了 java.net.Socket 和 java.net.ServerSocket 的底层实现，
 * 使用 NioSocketImpl 替代了原有的 PlainSocketImpl。
 * 新实现基于 NIO 实现，具有更好的性能和可维护性。
 *
 * 主要变化：
 * 1. 底层实现从 PlainSocketImpl 替换为 NioSocketImpl
 * 2. 保留了所有公开 API，对开发者透明
 * 3. 通过系统属性 jdk.net.usePlainSocketImpl 可回退到旧实现
 *
 * 演化历程: Socket API 重实现 JDK 13 STANDARD（JEP 353）
 *
 * @author lingwh
 * @date 2026/08/06 14:08
 */
public class SocketReimplTest {

    private static final int TEST_PORT = 18999;

    /**
     * 测试基本的 Socket 通信：客户端发送消息，服务端接收并回复
     *
     * 启动一个服务端线程监听端口，客户端连接后发送消息，
     * 验证 JDK 13 新的 NioSocketImpl 能够正常工作。
     */
    @Test
    public void testSocketBasicCommunication() throws Exception {
        // 启动服务端
        CompletableFuture<String> serverFuture = CompletableFuture.supplyAsync(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TEST_PORT);
                 Socket clientSocket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("服务端: 客户端已连接 - " + clientSocket.getInetAddress());
                System.out.println("服务端: Socket 实现类: " + clientSocket.getClass().getName());

                // 读取客户端消息
                String message = reader.readLine();
                System.out.println("服务端: 收到消息 - " + message);

                // 回复客户端
                writer.println("服务端已收到: " + message);
                return message;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // 稍微等待服务端启动
        Thread.sleep(500);

        // 客户端连接
        try (Socket socket = new Socket("localhost", TEST_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("客户端: Socket 实现类: " + socket.getClass().getName());

            // 发送消息
            String message = "Hello JDK 13 Socket Reimplementation!";
            writer.println(message);
            System.out.println("客户端: 发送消息 - " + message);

            // 接收回复
            String response = reader.readLine();
            System.out.println("客户端: 收到回复 - " + response);
        }

        // 等待服务端完成
        String result = serverFuture.get(5, TimeUnit.SECONDS);
        System.out.println("测试完成, 服务端接收到的消息: " + result);
    }

    /**
     * 测试 ServerSocket 的基本属性设置
     */
    @Test
    public void testServerSocketProperties() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(TEST_PORT + 1)) {
            // 设置超时
            serverSocket.setSoTimeout(1000);
            System.out.println("服务端超时时间: " + serverSocket.getSoTimeout() + "ms");

            // 设置重用地址
            serverSocket.setReuseAddress(true);
            System.out.println("地址重用: " + serverSocket.getReuseAddress());

            // 设置接收缓冲区大小
            serverSocket.setReceiveBufferSize(65536);
            System.out.println("接收缓冲区大小: " + serverSocket.getReceiveBufferSize() + " 字节");

            // 获取本地端口
            System.out.println("本地端口: " + serverSocket.getLocalPort());
            System.out.println("服务端 Socket 实现类: " + serverSocket.getClass().getName());
        }
    }

    /**
     * 测试 Socket 的 TCP 参数设置
     */
    @Test
    public void testSocketTcpParameters() throws Exception {
        // 测试前先启动一个服务端
        CompletableFuture<Void> serverFuture = CompletableFuture.runAsync(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TEST_PORT + 2)) {
                serverSocket.accept();
            } catch (IOException e) {
                // ignore
            }
        });

        Thread.sleep(500);

        try (Socket socket = new Socket("localhost", TEST_PORT + 2)) {
            // 设置 TCP no delay
            socket.setTcpNoDelay(true);
            System.out.println("TCP no delay: " + socket.getTcpNoDelay());

            // 设置 keep alive
            socket.setKeepAlive(true);
            System.out.println("Keep alive: " + socket.getKeepAlive());

            // 设置发送缓冲区大小
            socket.setSendBufferSize(32768);
            System.out.println("发送缓冲区大小: " + socket.getSendBufferSize() + " 字节");

            // 设置接收缓冲区大小
            socket.setReceiveBufferSize(32768);
            System.out.println("接收缓冲区大小: " + socket.getReceiveBufferSize() + " 字节");

            // 设置超时时间
            socket.setSoTimeout(5000);
            System.out.println("超时时间: " + socket.getSoTimeout() + "ms");

            // 设置 linger
            socket.setSoLinger(true, 3);
            System.out.println("SO_LINGER: " + socket.getSoLinger());

            // 获取连接信息
            System.out.println("本地地址: " + socket.getLocalAddress());
            System.out.println("远程地址: " + socket.getInetAddress());
            System.out.println("本地端口: " + socket.getLocalPort());
            System.out.println("远程端口: " + socket.getPort());
        }

        serverFuture.cancel(true);
    }

    /**
     * 测试 Socket 输入输出流的基本操作
     */
    @Test
    public void testSocketStreamOperations() throws Exception {
        CompletableFuture<Void> serverFuture = CompletableFuture.runAsync(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TEST_PORT + 3);
                 Socket client = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter writer = new PrintWriter(client.getOutputStream(), true)) {

                // 服务端依次读取多行
                String line;
                int lineCount = 0;
                while ((line = reader.readLine()) != null && lineCount < 3) {
                    System.out.println("服务端接收到: " + line);
                    writer.println("服务端确认行 " + (++lineCount) + ": " + line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(500);

        try (Socket socket = new Socket("localhost", TEST_PORT + 3);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            // 发送多行消息
            writer.println("第一行消息");
            System.out.println("客户端回复: " + reader.readLine());

            writer.println("第二行消息");
            System.out.println("客户端回复: " + reader.readLine());

            writer.println("第三行消息");
            System.out.println("客户端回复: " + reader.readLine());
        }

        serverFuture.get(5, TimeUnit.SECONDS);
    }

    /**
     * 测试 Socket 超时机制
     */
    @Test
    public void testSocketTimeout() throws Exception {
        // 启动一个服务端但只连接不发送数据
        CompletableFuture<Void> serverFuture = CompletableFuture.runAsync(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TEST_PORT + 4)) {
                Socket client = serverSocket.accept();
                System.out.println("服务端: 客户端已连接，但不发送数据以测试超时");
                // 保持连接但不发送数据
                Thread.sleep(3000);
                client.close();
            } catch (IOException | InterruptedException e) {
                // ignore
            }
        });

        Thread.sleep(500);

        try (Socket socket = new Socket("localhost", TEST_PORT + 4)) {
            // 设置超时时间为 1000ms
            socket.setSoTimeout(1000);
            System.out.println("客户端: 超时时间设置为 1000ms");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            long start = System.currentTimeMillis();
            try {
                // 尝试读取数据，预期会超时
                reader.readLine();
                System.out.println("未超时，读到了数据");
            } catch (java.net.SocketTimeoutException e) {
                long elapsed = System.currentTimeMillis() - start;
                System.out.println("客户端: 预期超时发生，耗时 " + elapsed + "ms");
                System.out.println("客户端: 超时异常: " + e.getMessage());
            }
        }

        serverFuture.cancel(true);
    }

    /**
     * 测试 Socket 关闭和 isClosed/isConnected 状态
     */
    @Test
    public void testSocketStateManagement() throws Exception {
        // 启动一个简单服务端
        CompletableFuture<Void> serverFuture = CompletableFuture.runAsync(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TEST_PORT + 5)) {
                serverSocket.accept().close();
            } catch (IOException e) {
                // ignore
            }
        });

        Thread.sleep(500);

        Socket socket = new Socket("localhost", TEST_PORT + 5);
        System.out.println("连接后 - isConnected: " + socket.isConnected());
        System.out.println("连接后 - isClosed: " + socket.isClosed());
        System.out.println("连接后 - isBound: " + socket.isBound());
        System.out.println("连接后 - isInputShutdown: " + socket.isInputShutdown());
        System.out.println("连接后 - isOutputShutdown: " + socket.isOutputShutdown());

        // 关闭输入流
        socket.shutdownInput();
        System.out.println("关闭输入后 - isInputShutdown: " + socket.isInputShutdown());

        // 关闭输出流
        socket.shutdownOutput();
        System.out.println("关闭输出后 - isOutputShutdown: " + socket.isOutputShutdown());

        // 关闭 Socket
        socket.close();
        System.out.println("关闭后 - isConnected: " + socket.isConnected());
        System.out.println("关闭后 - isClosed: " + socket.isClosed());

        serverFuture.cancel(true);
    }
}