package org.bluebridge.section_01_two_phase_termination.case_02;

/**
 * 测试两阶段终止线程
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TwoPhaseTerminationTest {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination.WorkerThread worker = new TwoPhaseTermination.WorkerThread();
        worker.start();

        // 模拟主线程运行一段时间
        Thread.sleep(2000);

        // 第一阶段：请求终止
        System.out.println("Requesting thread termination......");
        worker.terminate();

        // 第二阶段：等待工作线程完成清理工作并终止
        worker.join();
        System.out.println("Thread terminated......");
    }
}
