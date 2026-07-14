package org.bluebridge.thread.thread_designpattern.two_phrase_termination.tpt_a;

/**
 * 两阶段终止模式 - 计数器自增测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class CounterIncrementTest {

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();
        Thread.sleep(5_000L);
        counterIncrement.close();
    }
}
