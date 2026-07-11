package org.bluebridge.designpattern_02_guarded_suspension.guarded_suspension_b;

import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc 测试保护性暂停模式
 * @date 2026/7/9 00:00
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
                //在等待时间内
                TimeUnit.MILLISECONDS.sleep(1000);
                //超时的情况
                //TimeUnit.MILLISECONDS.sleep(3000);
                guardeObject.complete(new Object());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t2").start();

    }
}
