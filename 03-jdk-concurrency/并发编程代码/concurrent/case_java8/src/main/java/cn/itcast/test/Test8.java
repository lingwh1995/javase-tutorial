package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 线程睡眠测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
@Slf4j(topic = "c.Test8")
public class Test8 {

    public static void main(String[] args) throws InterruptedException {
        log.debug("enter");
        TimeUnit.SECONDS.sleep(1);
        log.debug("end");
//        Thread.sleep(1000);
    }
}
