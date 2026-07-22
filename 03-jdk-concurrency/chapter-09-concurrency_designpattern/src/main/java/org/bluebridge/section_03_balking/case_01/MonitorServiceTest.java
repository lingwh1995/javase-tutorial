package org.bluebridge.section_03_balking.case_01;

/**
 * 测试监控服务
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
