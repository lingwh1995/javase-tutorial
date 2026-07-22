package org.bluebridge.section_04_scheduled_thread_pool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时线程池测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ScheduledThreadPool01HelloWorldTest {

    public static void main(String[] args) {
        // 测试 ScheduledExecutorService.schedule()
        // testScheduled();

        // 测试 ScheduledExecutorService.scheduleAtFixedRate()
        // testScheduleAtFixedRate1();

        // 测试 ScheduledExecutorService.scheduleAtFixedRate()
        // testScheduleAtFixedRate2();

        testScheduleWithFixedDelay();
    }

    /**
     * ScheduledExecutorService.schedule()
     */
    private static void testScheduled() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        // 添加两个任务，希望它们都在 1s 后执行
        executor.schedule(() -> {
            System.out.println("任务1，执行时间：" + new Date());
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1000, TimeUnit.MILLISECONDS);
        executor.schedule(() -> {
            System.out.println("任务2，执行时间：" + new Date());
        }, 1000, TimeUnit.MILLISECONDS);

        executor.shutdown();
    }

    /**
     * 测试 ScheduledExecutorService.scheduleAtFixedRate()
     *
     * 任务执行时间没有超过间隔时间
     */
    private static void testScheduleAtFixedRate1() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss");
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        System.out.println("start......");
        pool.scheduleAtFixedRate(() -> {
            System.out.println(dtf.format(LocalDateTime.now()) + " running......");
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 测试 ScheduledExecutorService.scheduleAtFixedRate()
     *
     * 任务执行时间超过了间隔时间
     * 输出分析：一开始，延时 1s，接下来，由于任务执行时间 > 间隔时间，间隔被『撑』到了 2s
     */
    private static void testScheduleAtFixedRate2() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss");
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        System.out.println("start......");
        pool.scheduleAtFixedRate(() -> {
            System.out.println(dtf.format(LocalDateTime.now()) + " running......");

            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 测试 ScheduledExecutorService.scheduleWithFixedDelay()
     *
     * 输出分析：一开始，延时 1s，scheduleWithFixedDelay 的间隔是 上一个任务结束 <-> 延时 <-> 下一个任务开始 所 以间隔都是 3s
     */
    private static void testScheduleWithFixedDelay() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss");
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        System.out.println("start......");
        pool.scheduleWithFixedDelay(() -> {
            System.out.println(dtf.format(LocalDateTime.now()) + " running......");
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
}
