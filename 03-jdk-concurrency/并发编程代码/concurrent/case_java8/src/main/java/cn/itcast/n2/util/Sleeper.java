package cn.itcast.n2.util;

import java.util.concurrent.TimeUnit;

/**
 * 线程睡眠工具
 *
 * @author lingwh
 * @date 2025/2/7 22:18
 */
public class Sleeper {

    public static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(double i) {
        try {
            TimeUnit.MILLISECONDS.sleep((int) (i * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
