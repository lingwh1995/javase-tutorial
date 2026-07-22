package org.bluebridge.section_03_balking.case_02;

/**
 * 两阶段终止 + 犹豫模式
 *
 * 1. Balking（犹豫）模式
 *    用在一个线程发现另一个线程或本线程已经做了某一件相同的事，那么本线程就无需再做了，直接结束返回
 * 2. 两阶段终止模式 + 犹豫模式
 *    在一个线程t1中优雅地终止另一个线程t2，终止线程t2前，让线程t2有一个料理后事的机会
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TwoPhaseTermination {

    // 监控线程
    private Thread monitor;
    private volatile boolean stop = false;

    // 判断是否执行过start()
    private boolean starting = false;

    /**
     * 启动监控线程
     */
    public void start() {
        synchronized (this) {
            if (starting) {
                return;
            }
            starting = true;
        }
        monitor = new Thread(() -> {
            // 使用 while(true) 模拟程序正常执行
            while (true) {
                Thread current = Thread.currentThread();
                if (stop) {
                    releaseResource();
                    break;
                }

                try {
                    Thread.sleep(1000);
                    System.out.println("执行监控......");
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                    System.out.println("打断标记： " + current.isInterrupted());
                    // 重新设置打断标记
                    current.interrupt();
                    System.out.println("打断标记： " + current.isInterrupted());
                }
            }
        });

        monitor.start();
    }

    /**
     * 停止线程
     */
    public void stop() {
        stop = true;
    }

    /**
     * 执行释放资源操作
     * 尤其是释放锁资源，这样可以防止因为暴力终止线程而导致的死锁情况的发生
     */
    public void releaseResource() {
        System.out.println("释放资源......");
    }
}
