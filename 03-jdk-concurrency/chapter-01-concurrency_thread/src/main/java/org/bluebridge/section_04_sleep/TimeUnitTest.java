package org.bluebridge.section_04_sleep;

import java.util.concurrent.TimeUnit;

/**
 * 使用 TimeUnit.SECONDS.sleep(10) 代替 Thread.sleep() ,使得代码可读性更强
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TimeUnitTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("TimeUnit test start...");
        TimeUnit.MILLISECONDS.sleep(2000);
        // TimeUnit.SECONDS.sleep(2);
        System.out.println("TimeUnit test end...");
    }
}
