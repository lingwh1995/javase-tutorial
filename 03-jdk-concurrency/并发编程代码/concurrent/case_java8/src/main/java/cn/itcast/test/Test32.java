package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

import static cn.itcast.n2.util.Sleeper.sleep;

/**
 * volatile 可见性测试
 *
 * @author lingwh
 * @date 2025/2/7 18:17
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
            run = false; // 线程 t 不会如预想的停下来
    }
}
