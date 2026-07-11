package org.bluebridge.thread.thread_designpattern.thread_pre_message.thread_pre_message_b;

/**
 * @author lingwh
 * @desc Thread-Per-Message 模式主机
 * @date 2019/10/18 17:16
 */
public class Host {
    private final Helper helper = new Helper();

    public void request(final int count, final char c) {
        System.out.println("request(" + count + ", " + c + ") BEGIN");
        new Thread() {
            @Override
            public void run() {
                helper.handle(count, c);
            }
        }.start();
    }

    public static void main(String[] args) {}
}
