package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

/**
 * wait等待测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
@Slf4j(topic = "c.Test18")
public class Test18 {

    static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
