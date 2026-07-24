package org.bluebridge.section_11_get_id;

/**
 * getId() 获取线程 id
 *
 * @author lingwh
 * @date 2026/7/13 11:00
 */
public class ThreadGetIdTest {

    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("Hello World~"), "t1");
        System.out.println("Thread " + t.getName() + " 的id " + t.getId());
        System.out.println("Thread " + Thread.currentThread().getName() + " 的id " + Thread.currentThread().getId());
    }
}
