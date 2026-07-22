package org.bluebridge.thread.section_03_thread_designpattern.balking.balking_a;

import java.io.IOException;
import java.util.Random;

/**
 * 修改数据线程
 *
 * @author lingwh
 * @date 2019/10/16 15:44
 */
public class ChangeThread extends Thread {

    private final Data data;
    private final Random random = new Random();

    public ChangeThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                // 修改数据
                data.change("No." + i);
                Thread.sleep(random.nextInt(1000));
                // 显示的保存
                data.save();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
