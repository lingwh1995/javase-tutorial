package org.bluebridge.section_14_synchronized_lock_coarsening;

/**
 * 锁粗化
 *
 * 锁粗化是指，将多个连续的加锁、解锁操作连接在一起，扩展成一个范围更大的锁。
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class SynchronizedLockCoarseningTest {

    public static void main(String[] args) {
        testSynchronizedLockCoarsening();
    }

    /**
     * 测试由于锁粗化循环体中的锁会被粗化到for循环外执行
     */
    public static void testSynchronizedLockCoarsening() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            synchronized (SynchronizedLockCoarseningTest.class) {
                sb.append("i:" + i);
            }
        }
    }
}
