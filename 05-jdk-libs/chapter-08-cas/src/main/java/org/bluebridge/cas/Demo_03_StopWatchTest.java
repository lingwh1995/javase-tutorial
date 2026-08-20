package org.bluebridge.cas;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * 统计某段代码执行时间
 *
 * @author lingwh
 * @date 2026/7/22 18:29
 */
@Slf4j
public class Demo_03_StopWatchTest {

    /**
     * 测试 StopWatch HelloWorld 案例
     */
    @Test
    public void testStopWatchHelloWorld() throws InterruptedException {
        // 创建一个 StopWatch 对象
        StopWatch stopWatch = new StopWatch("秒表");
        // 开始计时
        stopWatch.start("task-1");
        task1();
        // 停止计时
        stopWatch.stop();
        log.info("任务名称：{}，执行时间：{}，花费总时间：{}，任务总数：{}", stopWatch.getLastTaskName(), stopWatch.getLastTaskTimeMillis(),
                stopWatch.getTotalTimeMillis(), stopWatch.getTaskCount());
    }

    /**
     * 测试 StopWatch
     * 1. 多个任务执行时间统计
     * 2. 优雅的打印执行结果
     */
    @Test
    public void testStopWatch() throws InterruptedException {
        // 创建一个 StopWatch 对象
        StopWatch stopWatch = new StopWatch("秒表");
        // task-1 开始计时
        stopWatch.start("task-1");
        task1();
        // task-1 停止计时
        stopWatch.stop();

        // task-2 开始计时
        stopWatch.start("task-2");
        task2();
        // task-2 停止计时
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

    /**
     * 任务一：睡眠 1000 毫秒
     */
    private void task1() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    /**
     * 任务二：睡眠 2000 毫秒
     */
    private void task2() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
    }
}
