package org.bluebridge.designpattern_03_balking.balking_a;

/**
 * @author lingwh
 * @desc 测试监控服务
 * @date 2026/7/9 00:00
 */
public class MonitorServiceTest {
    public static void main(String[] args) {
        MonitorService monitorService = new MonitorService();
        monitorService.start();
        monitorService.start();
        monitorService.start();
        monitorService.start();
    }
}
