package org.bluebridge.designpattern_03_balking.balking_a;

/**
 * 监控服务
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MonitorService {

    // 用来表示是否已经有线程已经在执行启动了
    private volatile boolean starting;

    public void start() {
        System.out.println("尝试启动监控线程......[当前状态：" + starting + "]");
        synchronized (this) {
            if (starting) {
                return;
            }
            starting = true;
        }
        // 其实synchronized外面还可以再套一层if，或者改为if(!starting)，if框后直接return
        // 真正启动监控线程...
    }
}
