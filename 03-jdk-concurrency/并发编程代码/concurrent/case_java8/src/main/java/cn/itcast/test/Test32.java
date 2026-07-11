package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

import static cn.itcast.n2.util.Sleeper.sleep;

/**
 * @author lingwh
 * @desc volatile可见性测试
 * @date 2026/7/9 00:00
 */
@Slf4j(topic = "c.Test32")
public class Test32 {
    // 易变
    static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while(true){
                    if(!run) {
                        break;
                    }
            }
        });
        t.start();

        sleep(1);
            run = false; // 线程t不会如预想的停下来
    }
}
