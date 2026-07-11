package org.bluebridge.unsafe_03_memory_barrier;

import org.bluebridge.util.UnsafeAccessor;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc Unsafe内存屏障测试
 * @date 2026/7/9 00:00
 */
public class UnsafeMemoryBarrierTest {
    public static void main(String[] args) {
        ChangeThread changeThread = new ChangeThread();
        new Thread(changeThread).start();
        while (true) {
            boolean flag = changeThread.isFlag();
            Unsafe unsafe = UnsafeAccessor.getUnsafe();
            unsafe.loadFence(); // 加入读内存屏障
            if (flag) {
                System.out.println("detected flag changed......");
                break;
            }
        }
        System.out.println("main thread end......");
    }
}
