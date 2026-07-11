package org.bluebridge.jmm_04_happens_before;

/**
 * happens-before锁监视器规则测试
 * 线程解锁 m 之前对变量的写，对于接下来对 m 加锁的其它线程对该变量的读可见(synchronized关键字的可见性、监视器规则)
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class HappensBeforeTest1 {
    private static int x;
    private static Object m = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized(m) {
                x = 10;
            }
        },"t1").start();

        new Thread(()->{
            synchronized(m) {
                System.out.println(x);
            }
        },"t2").start();
    }
}
