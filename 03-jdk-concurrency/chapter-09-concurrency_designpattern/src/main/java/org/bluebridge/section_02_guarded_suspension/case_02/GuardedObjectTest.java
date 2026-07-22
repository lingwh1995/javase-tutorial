package org.bluebridge.section_02_guarded_suspension.case_02;

import java.util.concurrent.TimeUnit;

/**
 * 测试保护性暂停模式
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class GuardedObjectTest {

    public static void main(String[] args) {
        // 线程1等待线程2的下载结果
        GuardedObject guardeObject = new GuardedObject();
        new Thread(() -> {
            System.out.println("等待结果......");
            Object obj = guardeObject.get(2000);
            System.out.println("结果是：" + obj);
        }, "t1").start();

        new Thread(() -> {
            System.out.println("执行下载......");
            try {
                // 在等待时间内
                TimeUnit.MILLISECONDS.sleep(1000);
                // 超时的情况
                // TimeUnit.MILLISECONDS.sleep(3000);
                guardeObject.complete(new Object());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t2").start();

    }
}
