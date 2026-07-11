package org.bluebridge.thread_15_make_tea;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc 泡茶线程
 * @date 2026/7/9 00:00
 */
public class MakeTeaThread {

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Thread t1 = new Thread(() -> {
            System.out.println("张三接冷水[开始]..... " + formatter.format( Calendar.getInstance().getTime()));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("张三接冷水[结束]..... " + formatter.format( Calendar.getInstance().getTime()));

            System.out.println("张三烧开水[开始]..... " + formatter.format( Calendar.getInstance().getTime()));
            try {
                TimeUnit.SECONDS.sleep(18);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("张三烧开水[结束]..... " + formatter.format( Calendar.getInstance().getTime()));
        },"t1");

        Thread t2 = new Thread(() -> {
            System.out.println("李四洗茶壶[开始]..... " + formatter.format( Calendar.getInstance().getTime()));
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("李四洗茶壶[结束]..... " + formatter.format( Calendar.getInstance().getTime()));

            System.out.println("李四洗茶杯[开始]..... " + formatter.format( Calendar.getInstance().getTime()));
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("李四洗茶杯[结束]..... " + formatter.format( Calendar.getInstance().getTime()));

            System.out.println("李四拿茶叶[开始]..... " + formatter.format( Calendar.getInstance().getTime()));
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("李四拿茶叶[结束]..... " + formatter.format( Calendar.getInstance().getTime()));

            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("李四泡茶叶..... " + formatter.format( Calendar.getInstance().getTime()));
        },"t2");

        t1.start();
        t2.start();
    }
}
