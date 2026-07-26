package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程中断测试
 *
 * @author lingwh
 * @date 2025/2/7 08:52
 */
@Slf4j(topic = "c.Test7")
public class Test7 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("enter sleep...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up...");
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        Thread.sleep(1000);
        log.debug("interrupt...");
        t1.interrupt();
    }
}
