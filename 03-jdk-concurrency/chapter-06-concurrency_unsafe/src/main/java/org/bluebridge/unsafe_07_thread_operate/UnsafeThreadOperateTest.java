package org.bluebridge.unsafe_07_thread_operate;

import java.util.concurrent.TimeUnit;
import org.bluebridge.util.UnsafeAccessor;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc Unsafe应用六 操作线程
 * @date 2026/7/9 00:00
 */
public class UnsafeThreadOperateTest {
    public static void main(String[] args) {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        Thread mainThread = Thread.currentThread();
        new Thread(()->{
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
