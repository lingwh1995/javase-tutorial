﻿package org.bluebridge.section_16_jdk16.unit_05_unix_domain;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * JDK 16 Unix-Domain Socket 测试（JEP 380，STANDARD 特性）
 * <p>
 * Unix-Domain Socket（也称为 IPC Socket）允许同一台主机上的进程之间
 * 通过本地套接字进行通信，比 TCP/IP 回环（localhost）通信更高效。
 * <p>
 * 主要 API：
 * - java.net.UnixDomainSocketAddress: Unix 域套接字地址
 * - java.net.StandardProtocolFamily.UNIX: Unix 协议族
 * - java.nio.channels.ServerSocketChannel: 服务端套接字通道
 * - java.nio.channels.SocketChannel: 客户端套接字通道
 * <p>
 * 注意：Windows 平台从 JDK 16 开始支持 Unix-Domain Socket，
 * 但使用的是 Windows 的 AF_UNIX 支持。
 * <p>
 * Unix-Domain Socket 特点：
 * 1. 比 TCP 回环更快（无需网络协议栈）
 * 2. 安全性更高（基于文件系统权限）
 * 3. 仅限本地通信
 *
 * @author lingwh
 * @date 2026/08/06 14:09
 */
public class UnixDomainSocketTest {

    /**
     * 测试使用 Unix-Domain Socket 进行基本通信
     * <p>
     * 通过 ServerSocketChannel 和 SocketChannel 使用 Unix 协议族
     * 建立本地套接字通信，验证基本的请求-响应模式。
     */
    @Test
    public void testUnixDomainSocketBasicCommunication() throws Exception {
        System.out.println("=== Unix-Domain Socket 基本通信测试 ===");

        // 创建临时文件路径作为 Unix 域套接字地址
        Path socketPath = Files.createTempFile("unix-socket", ".sock");
        Files.delete(socketPath); // 删除文件，只保留路径作为地址
        System.out.println("Socket 路径: " + socketPath);

        try {
            // 创建 Unix 域套接字地址
            UnixDomainSocketAddress serverAddress = UnixDomainSocketAddress.of(socketPath);

            // 启动服务端
            CompletableFuture<String> serverFuture = CompletableFuture.supplyAsync(() -> {
                try (ServerSocketChannel serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX);
                     SocketChannel clientChannel = serverChannel.accept()) {

                    serverChannel.bind(serverAddress);
                    System.out.println("服务端: Unix-Domain Socket 服务端已启动");

                    // 读取客户端消息
                    byte[] buffer = new byte[1024];
                    int bytesRead = clientChannel.read(java.nio.ByteBuffer.wrap(buffer));
                    String message = new String(buffer, 0, bytesRead);
                    System.out.println("服务端: 收到消息 - " + message);

                    // 回复客户端
                    String response = "服务端回复: " + message;
                    clientChannel.write(java.nio.ByteBuffer.wrap(response.getBytes()));
                    System.out.println("服务端: 发送回复 - " + response);

                    return message;
                } catch (IOException e) {
                    throw new RuntimeException("服务端异常", e);
                }
            });

            // 等待服务端绑定
            Thread.sleep(500);

            // 客户端连接
            try (SocketChannel clientChannel = SocketChannel.open(StandardProtocolFamily.UNIX)) {
                clientChannel.connect(serverAddress);
                System.out.println("客户端: 已连接到 Unix-Domain Socket 服务端");

                // 发送消息
                String message = "Hello Unix-Domain Socket!";
                clientChannel.write(java.nio.ByteBuffer.wrap(message.getBytes()));
                System.out.println("客户端: 发送消息 - " + message);

                // 接收回复
                byte[] buffer = new byte[1024];
                int bytesRead = clientChannel.read(java.nio.ByteBuffer.wrap(buffer));
                String response = new String(buffer, 0, bytesRead);
                System.out.println("客户端: 收到回复 - " + response);
            }

            // 等待服务端完成
            String result = serverFuture.get(5, TimeUnit.SECONDS);
            System.out.println("测试完成, 服务端接收到的消息: " + result);

        } finally {
            // 清理 socket 文件
            try {
                Files.deleteIfExists(socketPath);
            } catch (IOException e) {
                System.err.println("清理 socket 文件失败: " + e.getMessage());
            }
        }
    }

    /**
     * 测试使用 Unix-Domain Socket 进行多行通信
     */
    @Test
    public void testUnixDomainSocketMultiLineCommunication() throws Exception {
        System.out.println("=== Unix-Domain Socket 多行通信测试 ===");

        Path socketPath = Files.createTempFile("unix-multi", ".sock");
        Files.delete(socketPath);

        try {
            UnixDomainSocketAddress serverAddress = UnixDomainSocketAddress.of(socketPath);

            // 启动服务端
            CompletableFuture<Void> serverFuture = CompletableFuture.runAsync(() -> {
                try (ServerSocketChannel serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX)) {
                    serverChannel.bind(serverAddress);

                    try (SocketChannel clientChannel = serverChannel.accept()) {
                        System.out.println("服务端: 客户端已连接");

                        // 读取多行消息
                        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(1024);
                        StringBuilder received = new StringBuilder();

                        while (clientChannel.read(buffer) > 0) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            received.append(new String(bytes));
                            buffer.clear();

                            // 收到结束标记后退出
                            if (received.toString().contains("END")) {
                                break;
                            }
                        }

                        System.out.println("服务端: 完整接收消息 - " + received);

                        // 回复
                        String response = "服务端确认收到 " + received.toString().split("\n").length + " 行消息";
                        clientChannel.write(java.nio.ByteBuffer.wrap(response.getBytes()));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Thread.sleep(500);

            // 客户端发送多行消息
            try (SocketChannel clientChannel = SocketChannel.open(StandardProtocolFamily.UNIX)) {
                clientChannel.connect(serverAddress);

                String message = "第一行消息\n第二行消息\n第三行消息\nEND\n";
                clientChannel.write(java.nio.ByteBuffer.wrap(message.getBytes()));
                System.out.println("客户端: 已发送多行消息");

                // 读取回复
                java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(1024);
                int bytesRead = clientChannel.read(buffer);
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                System.out.println("客户端: 收到回复 - " + new String(bytes, 0, bytesRead));
            }

            serverFuture.get(5, TimeUnit.SECONDS);

        } finally {
            try {
                Files.deleteIfExists(socketPath);
            } catch (IOException e) {
                System.err.println("清理 socket 文件失败: " + e.getMessage());
            }
        }
    }

    /**
     * 测试 UnixDomainSocketAddress 的创建和属性
     */
    @Test
    public void testUnixDomainSocketAddress() throws Exception {
        System.out.println("=== UnixDomainSocketAddress 测试 ===");

        // 使用路径创建地址
        Path path = Path.of("/tmp/test-unix.sock");
        UnixDomainSocketAddress address = UnixDomainSocketAddress.of(path);
        System.out.println("Socket 地址: " + address);
        System.out.println("获取路径: " + address.getPath());
        System.out.println("路径是否相等: " + address.getPath().equals(path));

        // 使用字符串创建地址
        UnixDomainSocketAddress address2 = UnixDomainSocketAddress.of("/tmp/test-unix2.sock");
        System.out.println("第二个 Socket 地址: " + address2);
        System.out.println("第二个地址路径: " + address2.getPath());

        // 比较地址
        System.out.println("地址类型: " + address.getClass().getName());
        System.out.println("地址是否实现了 java.net.SocketAddress: " +
                (address instanceof java.net.SocketAddress));
    }

    /**
     * 测试 Unix-Domain Socket 的 StandardProtocolFamily
     */
    @Test
    public void testUnixDomainProtocolFamily() {
        System.out.println("=== Unix-Domain 协议族测试 ===");

        // 检查 StandardProtocolFamily.UNIX
        StandardProtocolFamily unixFamily = StandardProtocolFamily.UNIX;
        System.out.println("Unix 协议族名称: " + unixFamily.name());
        System.out.println("Unix 协议族: " + unixFamily);

        // 确认 UNIX 不同于 INET
        System.out.println("UNIX == INET: " + (unixFamily == StandardProtocolFamily.INET));
        System.out.println("UNIX 不是 INET 协议族");

        // 列出所有协议族
        System.out.println("所有协议族:");
        for (StandardProtocolFamily family : StandardProtocolFamily.values()) {
            System.out.println("  - " + family + " (" + family.name() + ")");
        }
    }

    /**
     * 测试 Unix-Domain Socket 的 ServerSocketChannel 配置
     */
    @Test
    public void testUnixDomainServerSocketChannel() throws Exception {
        System.out.println("=== Unix-Domain ServerSocketChannel 配置测试 ===");

        Path socketPath = Files.createTempFile("unix-config", ".sock");
        Files.delete(socketPath);

        try {
            UnixDomainSocketAddress serverAddress = UnixDomainSocketAddress.of(socketPath);

            try (ServerSocketChannel serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX)) {
                // 配置为非阻塞模式
                serverChannel.configureBlocking(false);
                System.out.println("非阻塞模式: " + !serverChannel.isBlocking());

                // 绑定地址
                serverChannel.bind(serverAddress);
                System.out.println("已绑定到: " + serverAddress.getPath());

                // 获取本地地址
                UnixDomainSocketAddress boundAddress = (UnixDomainSocketAddress) serverChannel.getLocalAddress();
                System.out.println("绑定的本地地址: " + boundAddress);
                System.out.println("绑定的路径: " + boundAddress.getPath());

                // 验证 ServerSocketChannel 属性
                System.out.println("ServerSocketChannel 是否打开: " + serverChannel.isOpen());
                System.out.println("ServerSocketChannel 支持的选项: " + serverChannel.supportedOptions());
            }

        } finally {
            try {
                Files.deleteIfExists(socketPath);
            } catch (IOException e) {
                System.err.println("清理 socket 文件失败: " + e.getMessage());
            }
        }
    }

    /**
     * 测试 Unix-Domain Socket 与 TCP Socket 的性能对比
     */
    @Test
    public void testUnixDomainVsTcpPerformance() throws Exception {
        System.out.println("=== Unix-Domain Socket vs TCP 性能对比 ===");

        // Unix-Domain Socket 测试
        Path socketPath = Files.createTempFile("unix-perf", ".sock");
        Files.delete(socketPath);

        try {
            UnixDomainSocketAddress serverAddress = UnixDomainSocketAddress.of(socketPath);

            // 启动 Unix-Domain 服务端
            CompletableFuture<Void> unixServerFuture = CompletableFuture.runAsync(() -> {
                try (ServerSocketChannel serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX)) {
                    serverChannel.bind(serverAddress);
                    try (SocketChannel clientChannel = serverChannel.accept()) {
                        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(1024);
                        clientChannel.read(buffer);
                        buffer.flip();
                        byte[] data = new byte[buffer.remaining()];
                        buffer.get(data);
                        clientChannel.write(java.nio.ByteBuffer.wrap("ok".getBytes()));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Thread.sleep(500);

            // Unix-Domain 客户端
            long unixStart = System.nanoTime();
            try (SocketChannel clientChannel = SocketChannel.open(StandardProtocolFamily.UNIX)) {
                clientChannel.connect(serverAddress);
                clientChannel.write(java.nio.ByteBuffer.wrap("hello".getBytes()));
                java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(1024);
                clientChannel.read(buffer);
            }
            long unixEnd = System.nanoTime();
            long unixDuration = unixEnd - unixStart;

            unixServerFuture.get(5, TimeUnit.SECONDS);
            System.out.println("Unix-Domain Socket 通信耗时: " + unixDuration / 1_000 + " μs");

            // TCP Socket 对比测试
            CompletableFuture<Void> tcpServerFuture = CompletableFuture.runAsync(() -> {
                try (ServerSocket serverSocket = new ServerSocket(18998)) {
                    try (Socket clientSocket = serverSocket.accept()) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                        reader.readLine();
                        writer.println("ok");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Thread.sleep(500);

            long tcpStart = System.nanoTime();
            try (Socket socket = new Socket("localhost", 18998);
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                writer.println("hello");
                reader.readLine();
            }
            long tcpEnd = System.nanoTime();
            long tcpDuration = tcpEnd - tcpStart;

            tcpServerFuture.cancel(true);
            System.out.println("TCP Socket 通信耗时: " + tcpDuration / 1_000 + " μs");

            System.out.println("性能对比: Unix-Domain 比 TCP 快 " +
                    ((tcpDuration - unixDuration) * 100 / tcpDuration) + "%");

        } finally {
            try {
                Files.deleteIfExists(socketPath);
            } catch (IOException e) {
                System.err.println("清理 socket 文件失败: " + e.getMessage());
            }
        }
    }
}