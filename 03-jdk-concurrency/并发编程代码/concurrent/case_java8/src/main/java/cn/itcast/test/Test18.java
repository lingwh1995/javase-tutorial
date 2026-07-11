package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc wait等待测试
 * @date 2026/7/9 00:00
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
