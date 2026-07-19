package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_b;

import java.util.Random;

/**
 * 写线程
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class WriteThread extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());
    private final Data shareData;
    private final String filler;
    private int index = 0;

    public WriteThread(Data shareData, String filler) {
        this.shareData = shareData;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                shareData.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
