package org.bluebridge.thread.thread_designpattern.two_phrase_termination.tpt_b;

import java.io.IOException;

/**
 * @author lingwh
 * @desc 两阶段终止模式 - 应用服务端客户端
 * @date 2026/7/9 00:00
 */
public class AppServerClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer server = new AppServer(8888);
        server.start();
        // 使用 telnet localhost 8888 连接到此程序
        Thread.sleep(45_000L);
        server.shutdown();
    }
}
