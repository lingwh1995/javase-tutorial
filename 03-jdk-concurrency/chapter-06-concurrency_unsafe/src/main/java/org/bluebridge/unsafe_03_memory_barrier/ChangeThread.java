package org.bluebridge.unsafe_03_memory_barrier;

import lombok.Getter;

/**
 * @author lingwh
 * @desc 修改标志线程
 * @date 2026/7/9 00:00
 */
@Getter
class ChangeThread implements Runnable {

    boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("subThread change flag to:" + flag);
        flag = true;
    }
}
