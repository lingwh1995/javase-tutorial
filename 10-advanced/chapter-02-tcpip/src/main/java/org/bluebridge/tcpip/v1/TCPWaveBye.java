package org.bluebridge.tcpip.v1;

import lombok.extern.slf4j.Slf4j;

/**
 * TCP四次挥手模拟
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@Slf4j
public class TCPWaveBye {

    // 连接状态枚举
    enum ConnectionState {
        ESTABLISHED,
        FIN_WAIT_1,
        FIN_WAIT_2,
        TIME_WAIT,
        CLOSE_WAIT,
        LAST_ACK,
        CLOSED
    }

    public static void fourWayWaveBye() {
        log.info("\n=== TCP 四次挥手模拟 ===");

        ConnectionState clientState = ConnectionState.ESTABLISHED;
        ConnectionState serverState = ConnectionState.ESTABLISHED;

        log.info("初始状态: Client={}, Server={}", clientState, serverState);

        // 1. 客户端发送FIN
        clientState = ConnectionState.FIN_WAIT_1;
        log.info("1. 客户端发送 FIN 包 -> 服务端");
        log.info("   客户端状态: {}", clientState);

        // 2. 服务端接收FIN，发送ACK
        serverState = ConnectionState.CLOSE_WAIT;
        log.info("2. 服务端接收 FIN，发送 ACK 包 -> 客户端");
        log.info("   服务端状态: {}", serverState);
        log.info("   客户端状态: {}", clientState = ConnectionState.FIN_WAIT_2);

        // 3. 服务端发送FIN
        log.info("3. 服务端发送 FIN 包 -> 客户端");
        log.info("   服务端状态: {}", serverState = ConnectionState.LAST_ACK);

        // 4. 客户端接收FIN，发送ACK
        log.info("4. 客户端接收 FIN，发送 ACK 包 -> 服务端");
        log.info("   客户端状态: {}", clientState = ConnectionState.TIME_WAIT);
        log.info("   服务端状态: {}", serverState = ConnectionState.CLOSED);

        // 5. 客户端等待2MSL后关闭
        log.info("5. 客户端等待 2MSL 时间后关闭");
        log.info("   客户端状态: {}", clientState = ConnectionState.CLOSED);

        log.info("连接关闭完成！");
    }
}
