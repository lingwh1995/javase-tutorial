package org.bluebridge.thread.thread_designpattern.balking.balking_a;

import java.io.IOException;

/**
 * 保存线程
 *
 * @author lingwh
 * @date 2019/10/16 15:42
 */
public class SaveThread extends Thread {

    private final Data data;

    public SaveThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                data.save();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
