package org.bluebridge.thread.thread_designpattern.thread_pre_message.thread_pre_message_b;

/**
 * @author lingwh
 * @desc Thread-Per-Message 模式客户端
 * @date 2019/10/18 17:24
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("MAIN-THREAD BEGIN......");
        Host host = new Host();
        host.request(10, 'A');
        host.request(20, 'B');
        host.request(30, 'C');
        System.out.println("MAIN-THREAD END......");
    }
}
