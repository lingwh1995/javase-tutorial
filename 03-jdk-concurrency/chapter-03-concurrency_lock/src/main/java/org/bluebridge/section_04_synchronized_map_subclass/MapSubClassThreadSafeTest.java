package org.bluebridge.section_04_synchronized_map_subclass;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map子类(HashMap、Hashtable、ConcurrentHashMap) 线程安全问题
 *
 * @author lingwh
 * @date 2026/4/21 09:45
 */
public class MapSubClassThreadSafeTest {

    public static void main(String[] args) {
        // 多线程环境下测试HashMap
        multiThreadHashMapTest();
        // 多线程环境下测试HashMap + synchronized
        multiThreadHashMapAndSynchronizedTest();
        // 多线程环境下测试HashTable
        multiThreadHashtableTest();
        // 多线程环境下测试ConcurrentHashMapTest
        multiThreadConcurrentHashMapTest();
    }

    /**
     * 多线程环境下测试HashMap，因为HashMap是非线程安全的，所以 map.size() 的值 不一定是 1000
     */
    private static void multiThreadHashMapTest() {
        Map<Integer, String> map = new HashMap<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, "value-" + i);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, "value-" + i);
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("map.size() : " + map.size());
    }

    /**
     * 多线程环境下测试HashMap + synchronized，因为HashMap是非线程安全的，所以 map.size() 的值 不一定是 1000
     */
    private static void multiThreadHashMapAndSynchronizedTest() {
        Map<Integer, String> map = new HashMap<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (MapSubClassThreadSafeTest.class) {
                    map.put(i, "value-" + i);
                }

            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (MapSubClassThreadSafeTest.class) {
                    map.put(i, "value-" + i);
                }
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("map.size() : " + map.size());
    }

    /**
     * 多线程环境下测试HashTable，因为HashTable是线程安全的，所以 table.size() 的值 一定是 1000
     */
    private static void multiThreadHashtableTest() {
        Map<Integer, String> table = new Hashtable<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                table.put(i, "value-" + i);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                table.put(i, "value-" + i);
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("map.size() : " + table.size());
    }

    /**
     * 多线程环境下测试HashTable，因为HashTable是线程安全的，所以 map.size() 的值 一定是 1000
     */
    private static void multiThreadConcurrentHashMapTest() {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, "value-" + i);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, "value-" + i);
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("map.size() : " + map.size());
    }
}
