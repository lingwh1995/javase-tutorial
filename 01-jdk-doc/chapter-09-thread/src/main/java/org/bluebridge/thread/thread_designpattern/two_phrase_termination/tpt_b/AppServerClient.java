package org.bluebridge.thread.thread_designpattern.two_phrase_termination.tpt_b;

import java.io.IOException;

/**
 * 两阶段终止模式 - 应用服务端客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
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
