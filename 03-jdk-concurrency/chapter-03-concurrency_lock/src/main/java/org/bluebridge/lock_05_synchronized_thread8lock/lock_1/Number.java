package org.bluebridge.lock_05_synchronized_thread8lock.lock_1;

/**
 * 情况1：12 或 21
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Number {

    public synchronized void a() {
        System.out.println("1......");
    }

    public synchronized void b() {
        System.out.println("2......");
    }

    public static void main(String[] args) {
        // 写法一
        // Number n1 = new Number();
        // new Thread(() -> { n1.a(); }).start();
        // new Thread(() -> { n1.b(); }).start();

        // 写法二
        Number number = new Number();
        new Thread(number::a).start();
        new Thread(number::b).start();
    }
}
