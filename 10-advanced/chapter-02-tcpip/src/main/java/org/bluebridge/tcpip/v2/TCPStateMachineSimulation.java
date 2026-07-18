package org.bluebridge.tcpip.v2;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 * TCP状态机模拟
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@Slf4j
public class TCPStateMachineSimulation {

    enum ConnectionState {
        CLOSED,
        LISTEN,
        SYN_SENT,
        SYN_RECEIVED,
        ESTABLISHED,
        FIN_WAIT_1,
        FIN_WAIT_2,
        CLOSE_WAIT,
        CLOSING,
        LAST_ACK,
        TIME_WAIT
    }

    static class DetailedTCPServer {
        private ServerSocket serverSocket;
        private volatile boolean isRunning = false;
        private ConnectionState state = ConnectionState.CLOSED;

        public void start(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            state = ConnectionState.LISTEN;
            isRunning = true;
            log.info("[服务器] 状态: {}，监听端口 {}", state, port);

            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    log.info("[服务器] 状态: {} -> SYN_RECEIVED", state);
                    state = ConnectionState.SYN_RECEIVED;

                    log.info("[服务器] 发送SYN+ACK");
                    state = ConnectionState.ESTABLISHED;
                    log.info("[服务器] 状态: {}，连接建立", state);

                    new DetailedConnectionHandler(clientSocket).start();
                } catch (IOException e) {
                    if (isRunning) {
                        log.info("服务器异常");
                    }
                }
            }
        }

        public void stop() throws IOException {
            isRunning = false;
            state = ConnectionState.CLOSED;
            if (serverSocket != null) {
                serverSocket.close();
            }
            log.info("[服务器] 状态: {}，服务器关闭", state);
        }

        class DetailedConnectionHandler extends Thread {
            private Socket clientSocket;
            private ConnectionState connectionState = ConnectionState.ESTABLISHED;

            public DetailedConnectionHandler(Socket socket) {
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
                        if ("FIN".equalsIgnoreCase(inputLine)) {
                            log.info("[服务器] 状态: {} -> CLOSE_WAIT", connectionState);
                            connectionState = ConnectionState.CLOSE_WAIT;
                            log.info("[服务器] 接收到客户端FIN");

                            log.info("[服务器] 发送ACK");
                            out.println("ACK");

                            log.info("[服务器] 状态: {} -> LAST_ACK", connectionState);
                            connectionState = ConnectionState.LAST_ACK;
                            log.info("[服务器] 发送服务器FIN");
                            out.println("FIN");

                            // 等待最终ACK
                            String finalAck = in.readLine();
                            if ("ACK".equalsIgnoreCase(finalAck)) {
                                log.info("[服务器] 状态: {} -> CLOSED", connectionState);
                                connectionState = ConnectionState.CLOSED;
                                log.info("[服务器] 接收到最终ACK，连接完全关闭");
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

    static class DetailedTCPClient {
        private Socket socket;
        private ConnectionState state = ConnectionState.CLOSED;

        public void connect(String host, int port) throws IOException {
            log.info("[客户端] 状态: {} -> SYN_SENT", state);
            state = ConnectionState.SYN_SENT;
            log.info("[客户端] 发送SYN请求连接");

            socket = new Socket(host, port);

            log.info("[客户端] 状态: {} -> ESTABLISHED", state);
            state = ConnectionState.ESTABLISHED;
            log.info("[客户端] 连接建立完成");
        }

        public void closeConnection() throws IOException {
            if (socket != null && !socket.isClosed() && state == ConnectionState.ESTABLISHED) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                log.info("[客户端] 状态: {} -> FIN_WAIT_1", state);
                state = ConnectionState.FIN_WAIT_1;
                log.info("[客户端] 发送FIN请求断开连接");
                out.println("FIN");

                // 接收服务器ACK
                String ack = in.readLine();
                log.info("[客户端] 状态: {} -> FIN_WAIT_2", state);
                state = ConnectionState.FIN_WAIT_2;
                log.info("[客户端] 接收服务器ACK: {}", ack);

                // 接收服务器FIN
                String serverFin = in.readLine();
                log.info("[客户端] 状态: {} -> TIME_WAIT", state);
                state = ConnectionState.TIME_WAIT;
                log.info("[客户端] 接收服务器FIN: {}", serverFin);

                // 发送最终ACK
                log.info("[客户端] 发送最终ACK");
                out.println("ACK");

                log.info("[客户端] 状态: {} -> CLOSED", state);
                state = ConnectionState.CLOSED;
                log.info("[客户端] 连接完全关闭");
            }
        }

        public void sendData(String data) throws IOException {
            if (socket != null && !socket.isClosed() && state == ConnectionState.ESTABLISHED) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(data);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                log.info("[客户端] 发送数据: {}，收到回复: {}", data, response);
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
                DetailedTCPServer server = new DetailedTCPServer();
                server.start(8080);

                // 运行一段时间后关闭服务器
                Thread.sleep(10000);
                server.stop();
            } catch (Exception e) {
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
                DetailedTCPClient client = new DetailedTCPClient();
                client.connect("localhost", 8080);

                // 发送一些数据
                client.sendData("Hello Server");
                client.sendData("This is a test message");

                // 等待一会儿再关闭连接
                Thread.sleep(2000);

                // 发起四次挥手
                client.closeConnection();
                client.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 运行一段时间后关闭
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }
}
