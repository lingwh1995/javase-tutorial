package org.bluebridge.tcpip.v1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc TCP连接状态模拟测试类
 * @date 2026/7/9 00:00
 */
@Slf4j
public class TCPConnectionSimulation {

    public static void main(String[] args) {
        // 模拟三次握手
        TCPHandshake.threeWayHandshake();

        // 模拟四次挥手
        TCPWaveBye.fourWayWaveBye();

        // 展示TCP状态机
        showTCPStateMachine();
    }

    /**
     * 展示TCP状态机
     */
    public static void showTCPStateMachine() {
        log.info("\n=== TCP 状态机 ===");
        log.info("三次握手状态转换:");
        log.info("  CLOSED -> SYN_SENT -> ESTABLISHED");
        log.info("  CLOSED -> LISTEN -> SYN_RECEIVED -> ESTABLISHED");

        log.info("\n四次挥手状态转换:");
        log.info("  ESTABLISHED -> FIN_WAIT_1 -> FIN_WAIT_2 -> TIME_WAIT -> CLOSED");
        log.info("  ESTABLISHED -> CLOSE_WAIT -> LAST_ACK -> CLOSED");
    }
}
