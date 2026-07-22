package org.bluebridge.thread.section_03_thread_designpattern.future.future_b;

/**
 * Future 模式客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class FutureClient {

    public static void main(String[] args) throws InterruptedException {
        futureTest1();
        // futureTest2();
    }

    /**
     * 测试Future模式
     * 具体的FutureTask使用匿名内部类实现
     *
     * @throws InterruptedException
     */
    private static void futureTest1() throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(
            () -> {
                try {
                    Thread.sleep(1_0000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "FINISHED!";
            });
        System.out.println("---------------------------");
        System.out.println("do other things......");
        Thread.sleep(1_000L);
        System.out.println("---------------------------");
        System.out.println(future.get());
    }

    /**
     * 测试Future模式
     * 具体的FutureTask使用具体的实现类传入
     *
     * @throws InterruptedException
     */
    private static void futureTest2() throws InterruptedException {
        FutureService futureService = new FutureService();
        // 创建具体的任务
        FutureTask futureTask = new FutureTaskImpl();
        Future<String> future = futureService.submit(futureTask);
        System.out.println("---------------------------");
        System.out.println("do other things......");
        Thread.sleep(1_000L);
        System.out.println("---------------------------");
        System.out.println(future.get());
    }
}
