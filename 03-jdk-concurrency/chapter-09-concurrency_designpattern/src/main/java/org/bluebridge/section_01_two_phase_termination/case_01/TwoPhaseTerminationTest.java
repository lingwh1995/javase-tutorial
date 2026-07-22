package org.bluebridge.section_01_two_phase_termination.case_01;

import java.util.concurrent.TimeUnit;

/**
 * 测试两阶段终止线程
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TwoPhaseTerminationTest {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        TimeUnit.MILLISECONDS.sleep(3500);
        tpt.stop();
    }
}
