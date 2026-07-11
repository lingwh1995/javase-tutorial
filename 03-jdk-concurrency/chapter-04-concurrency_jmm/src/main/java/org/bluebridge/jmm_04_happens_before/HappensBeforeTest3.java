package org.bluebridge.jmm_04_happens_before;

/**
 * happens-before线程启动规则测试
 * 线程 start 前对变量的写，对该线程开始后对该变量的读可见(程序顺序规则+线程启动规则)
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class HappensBeforeTest3 {
    private static int x;

    public static void main(String[] args) {
        x = 10;
        new Thread(()->{
            System.out.println(x);
        },"t2").start();
    }
}
