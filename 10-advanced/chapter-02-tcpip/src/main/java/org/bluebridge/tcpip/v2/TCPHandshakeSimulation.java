package org.bluebridge.tcpip.v2;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc TCP三次握手模拟
 * @date 2026/7/9 00:00
 */
@Slf4j
public class TCPHandshakeSimulation {

    // 服务器端 - 模拟三次握手过程
    static class TCPServer {
        private ServerSocket serverSocket;
        private boolean isRunning = false;

        public void start(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            log.info("[服务器] 启动，监听端口 {}", port);
            log.info("[服务器] 等待客户端连接请求...");

            while (isRunning) {
                try {
                    // 模拟第一次握手：接收客户端的SYN
                    Socket clientSocket = serverSocket.accept();
                    log.info("[服务器] 接收到客户端连接请求 (SYN)");

                    // 模拟第二次握手：发送SYN+ACK
                    log.info("[服务器] 发送SYN+ACK确认连接请求");

                    // 启动新线程处理客户端
                    new ClientHandler(clientSocket).start();
                } catch (IOException e) {
                    if (isRunning) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void stop() throws IOException {
            isRunning = false;
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    // 客户端处理器 - 模拟第三次握手
    static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            log.info("[服务器] 完成三次握手，连接建立");
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    log.info("[服务器] 收到客户端数据: {}", inputLine);
                    out.println("服务器回复: " + inputLine);

                    if ("bye".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                    log.info("[服务器] 客户端连接断开");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 客户端 - 发起三次握手
    static class TCPClient {
        private Socket socket;

        public void connect(String host, int port) throws IOException {
            log.info("[客户端] 发起连接请求 (SYN)");
            // 模拟第一次握手：发送SYN
            socket = new Socket(host, port);

            // 模拟第三次握手：接收服务器的ACK
            log.info("[客户端] 接收到服务器SYN+ACK，发送ACK确认");
            log.info("[客户端] 三次握手完成，连接建立");
        }

        public void sendData(String data) throws IOException {
            if (socket != null && !socket.isClosed()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(data);
                log.info("[客户端] 发送数据: {}", data);
            }
        }

        public String receiveData() throws IOException {
            if (socket != null && !socket.isClosed()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                log.info("[客户端] 接收数据: {}", response);
                return response;
            }
            return null;
        }

        public void disconnect() throws IOException {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                log.info("[客户端] 连接断开");
            }
        }
    }

    public static void main(String[] args) {
        // 启动服务器线程
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            try {
                TCPServer server = new TCPServer();
                server.start(8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 等待服务器启动
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动客户端
        executor.submit(() -> {
            try {
                TCPClient client = new TCPClient();
                client.connect("localhost", 8080);

                // 发送一些数据
                client.sendData("Hello Server");
                client.receiveData();

                client.sendData("bye");
                client.receiveData();

                // 断开连接
                client.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 运行一段时间后关闭
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }
}
