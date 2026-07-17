package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_b;

/**
 * 读线程
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ReaderThread extends Thread {

    private final Data shareData;

    public ReaderThread(Data shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuffer = shareData.read();
                System.out.println(Thread.currentThread().getName() + " reads:" + String.valueOf(readBuffer));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
