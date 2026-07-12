package org.bluebridge.designpattern_04_sequential_control.alternate_print;

/**
 * 交替打印(WaitNotify版)
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AlternatePrintWaitNotifyEditionTest {

    public static void main(String[] args) {
        SyncWaitNotify syncWaitNotify = new SyncWaitNotify(1, 5);
        new Thread(() -> {
            syncWaitNotify.print(1, 2, "a");
        }).start();
        new Thread(() -> {
            syncWaitNotify.print(2, 3, "b");
        }).start();
        new Thread(() -> {
            syncWaitNotify.print(3, 1, "c");
        }).start();
    }

    public static class SyncWaitNotify {

        private int flag;
        private int loopNumber;

        public SyncWaitNotify(int flag, int loopNumber) {
            this.flag = flag;
            this.loopNumber = loopNumber;
        }

        public void print(int waitFlag, int nextFlag, String str) {
            for (int i = 0; i < loopNumber; i++) {
                synchronized (this) {
                    while (this.flag != waitFlag) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(str);
                    flag = nextFlag;
                    this.notifyAll();
                }
            }
        }
    }
}
