package org.bluebridge.thread.thread_designpattern.two_phrase_termination.tpt_b;

import java.io.*;
import java.net.Socket;

/**
 * 两阶段终止模式 - 客户端处理器
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter printWriter = new PrintWriter(outputStream)) {
            while (running) {
                String message = br.readLine();
                if (message == null) {
                    break;
                }
                System.out.println("Come from client:" + message);
                printWriter.write("echo:" + message);
                printWriter.flush();
            }
        } catch (IOException e) {
            this.running = false;
        } finally {
            this.stop();
        }
    }

    public void stop() {
        if (running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.println("执行stop()失败！");
        }
    }
}
