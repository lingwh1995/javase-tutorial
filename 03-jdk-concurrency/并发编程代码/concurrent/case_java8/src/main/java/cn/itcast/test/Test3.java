package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 多线程运行测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
@Slf4j(topic = "c.Test3")
public class Test3 {

    public static void main(String[] args) {
        new Thread(() -> {
            while(true) {
                log.debug("running...");
            }
        }, "t1").start();

        new Thread(() -> {
            while(true) {
                log.debug("running...");
            }
        }, "t2").start();
    }
}
