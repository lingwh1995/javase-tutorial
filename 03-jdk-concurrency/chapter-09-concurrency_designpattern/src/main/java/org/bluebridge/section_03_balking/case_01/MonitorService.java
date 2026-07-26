package org.bluebridge.section_03_balking.case_01;

/**
 * 监控服务
 *
 * @author lingwh
 * @date 2025/3/10 11:32
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
        // 其实 synchronized 外面还可以再套一层 if，或者改为 if(!starting)，if 框后直接 return
        // 真正启动监控线程...
    }
}
