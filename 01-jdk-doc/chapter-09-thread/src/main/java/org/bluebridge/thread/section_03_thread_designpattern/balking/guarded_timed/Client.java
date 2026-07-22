package org.bluebridge.thread.section_03_thread_designpattern.balking.guarded_timed;

import java.util.concurrent.TimeoutException;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2019/10/17 10:51
 */
public class Client {

    public static void main(String[] args) {
        Host host = new Host(10000);
        try {
            System.out.println("execute BEGIN......");
            host.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
