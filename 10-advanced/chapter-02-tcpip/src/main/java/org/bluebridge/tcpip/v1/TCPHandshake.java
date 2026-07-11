package org.bluebridge.tcpip.v1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc TCP三次握手模拟
 * @date 2026/7/9 00:00
 */
@Slf4j
public class TCPHandshake {

    // 客户端状态枚举
    enum ClientState {
        CLOSED,
        SYN_SENT,
        ESTABLISHED
    }

    // 服务端状态枚举
    enum ServerState {
        CLOSED,
        LISTEN,
        SYN_RECEIVED,
        ESTABLISHED
    }

    public static void threeWayHandshake() {
        log.info("=== TCP 三次握手模拟 ===");

        ClientState clientState = ClientState.CLOSED;
        ServerState serverState = ServerState.CLOSED;

        log.info("初始状态: Client={}, Server={}", clientState, serverState);

        // 1. 客户端发送SYN
        clientState = ClientState.SYN_SENT;
        log.info("1. 客户端发送 SYN 包 -> 服务端");
        log.info("   客户端状态: {}", clientState);

        // 2. 服务端接收SYN，发送SYN+ACK
        serverState = ServerState.SYN_RECEIVED;
        log.info("2. 服务端接收 SYN，发送 SYN+ACK 包 -> 客户端");
        log.info("   服务端状态: {}", serverState);

        // 3. 客户端接收SYN+ACK，发送ACK
        clientState = ClientState.ESTABLISHED;
        log.info("3. 客户端接收 SYN+ACK，发送 ACK 包 -> 服务端");
        log.info("   客户端状态: {}", clientState);

        // 4. 服务端接收ACK
        serverState = ServerState.ESTABLISHED;
        log.info("4. 服务端接收 ACK");
        log.info("   服务端状态: {}", serverState);

        log.info("连接建立成功！");
    }
}
