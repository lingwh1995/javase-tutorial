package org.bluebridge.section_07_thread_operate;

import org.bluebridge.util.UnsafeAccessor;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

/**
 * Unsafe 应用六 操作线程
 *
 * @author lingwh
 * @date 2026/7/13 12:45
 */
public class UnsafeThreadOperateTest {

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        Thread mainThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("subThread try to unpark mainThread......");
                unsafe.unpark(mainThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("park main mainThread......");
        unsafe.park(false, 0L);
        System.out.println("unpark mainThread success......");
    }
}
