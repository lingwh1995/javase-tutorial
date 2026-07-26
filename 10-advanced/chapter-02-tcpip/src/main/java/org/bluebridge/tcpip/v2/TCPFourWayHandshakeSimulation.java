package org.bluebridge.tcpip.v2;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 * TCP 四次挥手模拟
 *
 * @author lingwh
 * @date 2025/9/17 10:47
 */
@Slf4j
public class TCPFourWayHandshakeSimulation {

    static class TCPServerWithFourWayHandshake {
        private ServerSocket serverSocket;
        private volatile boolean isRunning = false;

        public void start(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            log.info("[服务器] 启动，监听端口 {}", port);

            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    log.info("[服务器] 接收到客户端连接请求");
                    new ConnectionHandler(clientSocket).start();
                } catch (IOException e) {
                    if (isRunning) {
                        log.info("[服务器] 服务器关闭");
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

        // 连接处理器 - 模拟四次挥手
        class ConnectionHandler extends Thread {
            private Socket clientSocket;

            public ConnectionHandler(Socket socket) {
                this.clientSocket = socket;
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
                        log.info("[服务器] 收到数据: {}", inputLine);

                        if ("FIN".equalsIgnoreCase(inputLine)) {
                            log.info("[服务器] 接收到客户端FIN请求");
                            log.info("[服务器] 发送ACK确认");
                            out.println("ACK");

                            log.info("[服务器] 发送服务器FIN请求");
                            out.println("FIN");

                            // 等待客户端最后的ACK
                            String finalAck = in.readLine();
                            if ("ACK".equalsIgnoreCase(finalAck)) {
                                log.info("[服务器] 接收到客户端最终ACK");
                                log.info("[服务器] 四次挥手完成，连接关闭");
                            }
                            break;
                        } else {
                            out.println("服务器回复: " + inputLine);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class TCPClientWithFourWayHandshake {
        private Socket socket;

        public void connect(String host, int port) throws IOException {
            log.info("[客户端] 连接到服务器");
            socket = new Socket(host, port);
        }

        public void initiateFourWayHandshake() throws IOException {
            if (socket != null && !socket.isClosed()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                log.info("[客户端] 发送FIN请求断开连接");
                out.println("FIN");

                // 接收服务器ACK
                String ack = in.readLine();
                log.info("[客户端] 接收服务器ACK: {}", ack);

                // 接收服务器FIN
                String serverFin = in.readLine();
                log.info("[客户端] 接收服务器FIN: {}", serverFin);

                // 发送最终ACK
                log.info("[客户端] 发送最终ACK");
                out.println("ACK");

                log.info("[客户端] 四次挥手完成，连接关闭");
            }
        }

        public void sendData(String data) throws IOException {
            if (socket != null && !socket.isClosed()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(data);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                log.info("[客户端] 收到回复: {}", response);
            }
        }

        public void close() throws IOException {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 启动服务器
        executor.submit(() -> {
            try {
                TCPServerWithFourWayHandshake server = new TCPServerWithFourWayHandshake();
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
                TCPClientWithFourWayHandshake client = new TCPClientWithFourWayHandshake();
                client.connect("localhost", 8080);

                // 发送一些数据
                client.sendData("Hello Server");
                client.sendData("How are you?");

                // 发起四次挥手
                client.initiateFourWayHandshake();
                client.close();

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
