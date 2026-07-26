package org.bluebridge.section_03_balking.case_01;

/**
 * 测试监控服务
 *
 * @author lingwh
 * @date 2025/3/10 16:08
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
