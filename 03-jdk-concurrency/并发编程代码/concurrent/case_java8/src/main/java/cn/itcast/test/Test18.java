package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

/**
 * wait 等待测试
 *
 * @author lingwh
 * @date 2025/2/7 16:03
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
