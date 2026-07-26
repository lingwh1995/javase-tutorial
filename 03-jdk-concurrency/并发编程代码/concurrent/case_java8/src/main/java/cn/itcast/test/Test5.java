package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程状态测试
 *
 * @author lingwh
 * @date 2025/2/7 10:23
 */
@Slf4j(topic = "c.Test5")
public class Test5 {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running...");
            }
        };

        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
    }
}
