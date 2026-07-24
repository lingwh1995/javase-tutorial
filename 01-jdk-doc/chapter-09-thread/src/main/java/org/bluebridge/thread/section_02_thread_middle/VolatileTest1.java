package org.bluebridge.thread.section_02_thread_middle;

/**
 * volatile 关键字
 *
 * 1. 保证重排序的时候不会把后面的指令放在屏障的前面，也不会把前面的放到后面
 * 2. 强制对外缓存的修改操作立即写入到主存
 * 3. 如果是写操作，会导致其他 CPU 中的缓存失效
 * 4. 使用场景:
 *    - 状态量的标记
 *    - 屏障前后的一致性
 *
 * @author lingwh
 * @date 2019/10/15 10:30
 */
public class VolatileTest1 {

    private volatile static int INIT_VALUE = 0;
    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value updated to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READED").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update the value to[%d]\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATE").start();
    }
}
