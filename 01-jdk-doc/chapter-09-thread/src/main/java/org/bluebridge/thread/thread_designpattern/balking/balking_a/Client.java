package org.bluebridge.thread.thread_designpattern.balking.balking_a;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2019/10/16 17:39
 */
public class Client {

    public static void main(String[] args) {
        Data data = new Data("data.txt", "(empty)");
        new ChangeThread("ChangerThread", data).start();
        new SaveThread("SaveThread", data).start();
    }
}
