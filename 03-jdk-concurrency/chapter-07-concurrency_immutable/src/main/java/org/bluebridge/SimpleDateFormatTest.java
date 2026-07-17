package org.bluebridge;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat.parse()线程安全问题以及解决 方式一：使用同步锁解决线程安全问题
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SimpleDateFormatTest {

    public static void main(String[] args) throws ParseException {
        testSimpleDateFormatThreadUnSafe();

        // testSimpleDateFormatThreadSafe();
    }

    /**
     * 测试在线程不安全情况下使用 SimpleDateFormat.parse()
     */
    private static void testSimpleDateFormatThreadUnSafe() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 测试多线程情况下 sdf.parse()
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("parse = " + sdf.parse("1995-09-17"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    /**
     * 测试在线程安全情况下使用 SimpleDateFormat.parse()
     */
    private static void testSimpleDateFormatThreadSafe() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 使用 synchronized 解决线程安全问题
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                synchronized (sdf) {
                    try {
                        System.out.println("parse = " + sdf.parse("1995-09-17"));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }
}
