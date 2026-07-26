package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

import static cn.itcast.n2.util.Sleeper.sleep;

import java.util.concurrent.locks.LockSupport;

/**
 * park 打断测试
 *
 * @author lingwh
 * @date 2025/2/7 11:36
 */
@Slf4j(topic = "c.Test14")
public class Test14 {

    private static void test4() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...");
                LockSupport.park();
                log.debug("打断状态：{}", Thread.interrupted());
            }
        });
        t1.start();


        sleep(1);
        t1.interrupt();
    }

    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        sleep(1);
        t1.interrupt();

    }

    public static void main(String[] args) throws InterruptedException {
        test4();
    }
}
