package org.bluebridge.cas;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * @author lingwh
 * @desc 线程睡眠
 * @date 2026/7/10 00:00
 */
@Slf4j
public class _02_ThreadSleepTest {

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
