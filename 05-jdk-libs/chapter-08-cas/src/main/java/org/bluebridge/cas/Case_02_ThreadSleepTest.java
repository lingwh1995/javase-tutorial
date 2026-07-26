package org.bluebridge.cas;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * 线程睡眠
 *
 * @author lingwh
 * @date 2026/7/22 20:07
 */
@Slf4j
public class Case_02_ThreadSleepTest {

    /**
     * 不推荐使用
     *
     * @throws InterruptedException
     */
    @Test
    public void testThreadSleep1() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("task-1");
        Thread.sleep(2000);
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
    }

    /**
     * 推荐使用
     *
     * @throws InterruptedException
     */
    @Test
    public void testThreadSleep2() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("task-1");
        TimeUnit.MILLISECONDS.sleep(2000);
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
    }
}
