package org.bluebridge.thread.section_03_thread_designpattern.future.future_c;

/**
 * Future 模式客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class FutureClient {

    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(1_0000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISHED!";
        }, System.out::println);
        System.out.println("---------------------------");
        System.out.println("do other things......");
        Thread.sleep(1_000L);
        System.out.println("---------------------------");
        // System.out.println(future.get());
    }
}
