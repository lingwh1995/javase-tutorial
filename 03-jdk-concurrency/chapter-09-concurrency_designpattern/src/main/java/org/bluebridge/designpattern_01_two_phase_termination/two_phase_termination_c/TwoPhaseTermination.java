package org.bluebridge.designpattern_01_two_phase_termination.two_phase_termination_c;

/**
 * 两阶段终止模式说明
 *
 * 在一个线程t1中优雅地终止另一个线程t2，终止线程t2前，让线程t2有一个料理后事的机会
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class TwoPhaseTermination {
    // 监控线程
    private Thread monitor;
    private volatile boolean stop = false;

    /**
     * 启动监控线程
     */
    public void start() {
        monitor = new Thread(() -> {
           //使用 while(true) 模拟程序正常执行
           while (true) {
               Thread current = Thread.currentThread();
               if(stop) {
                   releaseResource();
                   break;
               }

               try {
                   Thread.sleep(1000);
                   System.out.println("执行监控......");
               } catch (InterruptedException e) {
                   //e.printStackTrace();
                   System.out.println("打断标记： " + current.isInterrupted());
                   //重新设置打断标记
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
        // 让线程立即停止而不是等待sleep结束
        monitor.interrupt();
    }

    /**
     * 执行释放资源操作
     *     尤其是释放锁资源，这样可以防止因为暴力终止线程而导致的死锁情况的发生
     */
    public void releaseResource() {
        System.out.println("释放资源......");
    }
}
