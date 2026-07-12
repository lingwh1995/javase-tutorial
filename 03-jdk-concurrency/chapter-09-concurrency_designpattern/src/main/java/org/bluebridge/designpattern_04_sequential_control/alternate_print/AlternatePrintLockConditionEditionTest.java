package org.bluebridge.designpattern_04_sequential_control.alternate_print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印(LockCondition版)
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AlternatePrintLockConditionEditionTest {

    public static void main(String[] args) {
        AwaitSignal as = new AwaitSignal(5);
        Condition aWaitSet = as.newCondition();
        Condition bWaitSet = as.newCondition();
        Condition cWaitSet = as.newCondition();
        new Thread(() -> {
            as.print("a", aWaitSet, bWaitSet);
        }).start();
        new Thread(() -> {
            as.print("b", bWaitSet, cWaitSet);
        }).start();
        new Thread(() -> {
            as.print("c", cWaitSet, aWaitSet);
        }).start();
        as.start(aWaitSet);
    }

    private static class AwaitSignal extends ReentrantLock {
        public void start(Condition first) {
            this.lock();
            try {
                System.out.println("start......");
                first.signal();
            } finally {
                this.unlock();
            }
        }

        public void print(String str, Condition current, Condition next) {
            for (int i = 0; i < loopNumber; i++) {
                this.lock();
                try {
                    current.await();
                    System.out.print(str);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.unlock();
                }
            }
        }

        // 循环次数
        private int loopNumber;

        public AwaitSignal(int loopNumber) {
            this.loopNumber = loopNumber;
        }
    }
}
