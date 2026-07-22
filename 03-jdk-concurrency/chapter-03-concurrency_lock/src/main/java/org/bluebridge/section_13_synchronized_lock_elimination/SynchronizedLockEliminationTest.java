package org.bluebridge.section_13_synchronized_lock_elimination;

/**
 * 锁消除
 *
 * 锁消除指的是在某些情况下，JVM虚拟机如果检测不到某段代码被共享和竞争的可能性，就会将这段代码所属的同步锁消除掉，从而到底提高程序性能的目的。
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class SynchronizedLockEliminationTest {

    public static void main(String[] args) {
        testSynchronizedLockElimination();
    }

    /**
     * 测试由于锁消除StringBuffer在编译时被StringBuilder替换
     */
    public static void testSynchronizedLockElimination() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append("i:" + i);
        }
    }
}
