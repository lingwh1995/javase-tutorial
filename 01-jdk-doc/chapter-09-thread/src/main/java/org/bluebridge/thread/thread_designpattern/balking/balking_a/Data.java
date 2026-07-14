package org.bluebridge.thread.thread_designpattern.balking.balking_a;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Balking 模式数据类
 *
 * @author lingwh
 * @date 2019/10/16 15:32
 */
public class Data {

    private final String filename;
    private String content;

    /**
     * 修改后的内容若未保存，则为true
     */
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String content) {
        this.content = content;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + "calls doSave,content = " + content);
        FileWriter writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
}
