package org.bluebridge.thread.thread_designpattern.future.future_a;

/**
 * @author lingwh
 * @desc 不使用 Future 模式会出现阻塞的情况
 * @date 2026/7/9 00:00
 */
public class NoFutureTest {
    public static void main(String[] args) throws InterruptedException {
        String result = get();
        System.out.println(result);
    }

    /**
     * A操作需要B操作返回的结果,但是B操作阻塞了,会导致A操作也阻塞住
     *
     * @return
     * @throws InterruptedException
     */
    public static String get() throws InterruptedException {
        // 模拟一个很费时间的操作,如从数据库中查询数据,但是由于网络问题导致查询速度非常慢
        System.out.println("正在查询数据......");
        Thread.sleep(1_0000L);
        System.out.println("查询数据完成......");
        return "FINISH!";
    }
}
