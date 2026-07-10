package org.bluebridge.thread.thread_designpattern.two_phrase_termination.tpt_b;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lingwh
 * @desc 两阶段终止模式 - 应用服务端
 * @date 2026/7/9 00:00
 */
public class AppServer extends Thread {
    private final int port;
    private static final int DEFAULT_PORT = 8080;
    private volatile boolean start = true;
    private List<ClientHandler> clientHandlers = new ArrayList<>();
    private final ExecutorService executors = Executors.newFixedThreadPool(10);
    private ServerSocket server;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
            while (start) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executors.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            this.dispose();
        }
    }

    /**
     * 销毁
     */
    private void dispose() {
        clientHandlers.stream().forEach(ClientHandler::stop);
        this.executors.shutdown();
    }

    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.server.close();
    }
}
