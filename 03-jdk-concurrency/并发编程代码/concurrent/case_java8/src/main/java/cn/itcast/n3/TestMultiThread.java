package cn.itcast.n3;

import lombok.extern.slf4j.Slf4j;

/**
 * 多线程测试
 *
 * @author lingwh
 * @date 2025/2/7 15:22
 */
@Slf4j(topic = "c.TestMultiThread")
public class TestMultiThread {

    public static void main(String[] args) {
        new Thread(() -> {
            while(true) {
                log.debug("running");
            }
        },"t1").start();
        new Thread(() -> {
            while(true) {
                log.debug("running");
            }
        },"t2").start();
    }
}
