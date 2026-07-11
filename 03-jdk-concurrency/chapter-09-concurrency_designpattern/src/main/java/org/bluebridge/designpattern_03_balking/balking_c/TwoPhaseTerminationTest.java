package org.bluebridge.designpattern_03_balking.balking_c;

import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc 测试两阶段终止线程
 * @date 2026/7/9 00:00
 */
public class TwoPhaseTerminationTest {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        // 第一次启动线程
        tpt.start();
        // 第二次启动线程
        tpt.start();

        TimeUnit.MILLISECONDS.sleep(3500);
        tpt.stop();
    }
}
