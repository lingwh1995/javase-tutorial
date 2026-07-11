package org.bluebridge.designpattern_01_two_phase_termination.two_phase_termination_a;

import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc 测试两阶段终止线程
 * @date 2026/7/9 00:00
 */
public class TwoPhaseTerminationTest {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        TimeUnit.MILLISECONDS.sleep(3500);
        tpt.stop();
    }
}
