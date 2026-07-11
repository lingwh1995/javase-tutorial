package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc 创建线程两种方式测试
 * @date 2026/7/9 00:00
 */
@Slf4j(topic = "c.Test1")
public class Test1 {

    public static void test2() {
        Thread t = new Thread(()->{ log.debug("running"); }, "t2");
        t.start();
    }

    public static void test1() {
        Thread t = new Thread(){
            @Override
            public void run() {
                log.debug("running");
            }
        };
        t.setName("t1");
        t.start();

    }
}
