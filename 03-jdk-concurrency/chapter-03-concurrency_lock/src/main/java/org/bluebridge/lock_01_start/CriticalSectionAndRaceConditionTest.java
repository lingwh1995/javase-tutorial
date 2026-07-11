package org.bluebridge.lock_01_start;

/**
 * 临界区和竞态条件测试
 *
 * 1. 临界区（Critical Section）
 *        一个程序运行多个线程本身是没有问题的，问题出在多个线程访问共享资源，多个线程读共享资源其实也没有问题，在多个线程对共享资源读
 *        写操作时发生指令交错，就会出现问题，一段代码块内如果存在对共享资源的多线程读写操作，称这段代码块为临界区
 * 2. 竞态条件（Race Condition）
 *        多个线程在临界区内执行，由于代码的执行序列不同而导致结果无法预测，称之为发生了竞态条件
 * 3. 不使用锁测试
 *        由于临界区的竞态条件而导致 counter 的值是无法预料的
 * 4. 为了避免临界区中发生竞态条件，可以使用下面方法来处理
 *        阻塞式的解决方案：synchronized，Lock
 *        非阻塞式的解决方案：原子变量
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class CriticalSectionAndRaceConditionTest {

    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter++;
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter--;
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("counter = " + counter);
    }
}
