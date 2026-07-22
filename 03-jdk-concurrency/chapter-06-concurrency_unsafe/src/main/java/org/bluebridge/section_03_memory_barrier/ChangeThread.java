package org.bluebridge.section_03_memory_barrier;

import lombok.Getter;

/**
 * 修改标志线程
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
