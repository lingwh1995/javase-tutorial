package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程测试
 *
 * @author lingwh
 * @date 2025/2/7 14:32
 */
@Slf4j(topic = "c.Test2")
public class Test2 {

    public static void main(String[] args) {
        Runnable r = () -> {log.debug("running");};

        Thread t = new Thread(r, "t2");

        t.start();
    }
}
