package org.bluebridge.thread.section_03_thread_designpattern.future.future_d;

/**
 * Future 模式客户端
 *
 * @author lingwh
 * @date 2019/10/10 16:56
 */
public class FutureClient {

    public static void main(String[] args) {
        FutureService futureService = new FutureService();
        Data data = futureService.request("hello,world");
        System.out.println("请求发送成功...");
        System.out.println("干其他的事情...");
        String result = data.getRequest();
        System.out.println(result);
    }
}
