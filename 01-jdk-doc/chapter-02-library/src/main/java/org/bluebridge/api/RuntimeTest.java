package org.bluebridge.api;

/**
 * @author lingwh
 * @desc 退出时增加钩子程序
 * @date 2026/7/9 00:00
 */
public class RuntimeTest {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("程序执行完成......");
        }));
        System.out.println("程序执行中......");
    }
}
