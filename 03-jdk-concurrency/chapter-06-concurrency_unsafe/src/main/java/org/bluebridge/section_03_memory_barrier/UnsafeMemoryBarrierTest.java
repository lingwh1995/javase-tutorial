package org.bluebridge.section_03_memory_barrier;

import org.bluebridge.util.UnsafeAccessor;
import sun.misc.Unsafe;

/**
 * Unsafe内存屏障测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
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
