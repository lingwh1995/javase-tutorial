package org.bluebridge.lock_10_synchronized_monitor;

/**
 * 同步代码块Monitor测试
 *
 * 1. 终端中输入如下命令
 *    javac .\MonitorSynchronizedCodeBlockTest.java -> javap -c .\MonitorSynchronizedCodeBlockTest.class
 * 2. 得到如下字节码（只展示核心部分）
 *    public void testMonitor();
 *      Code:
 *         0: aload_0
 *         1: dup
 *         2: astore_1
 *         3: monitorenter
 *         4: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         7: ldc           #13                 // String hello monitor......
 *         9: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *        12: aload_1
 *        13: monitorexit
 *        14: goto          22
 *        17: astore_2
 *        18: aload_1
 *        19: monitorexit
 *        20: aload_2
 *        21: athrow
 *        22: return
 *      Exception table:
 *         from    to  target type
 *             4    14    17   any
 *            17    20    17   any
 * 3. 可以观察到同步代码块中通过 monitorenter 和 monitorexit 实现同步
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MonitorSynchronizedCodeBlockTest {

    public void testMonitor() {
        synchronized (this) {
            System.out.println("hello monitor......");
        }
    }
}
