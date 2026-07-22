package org.bluebridge.thread.section_03_thread_designpattern.readwritelock;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库
 *
 * @author lingwh
 * @date 2019/10/18 16:48
 */
public class Database<K, V> {

    private final Map<K, V> map = new HashMap<K, V>();

    /**
     * 全部清除
     */
    public synchronized void clear() {
        verySlowly();
        map.clear();
    }

    /**
     * 给key分配value
     *
     * @param key
     * @param value
     */
    public synchronized void assign(K key, V value) {
        verySlowly();
        map.put(key, value);
    }

    /**
     * 获取给key分配的值
     *
     * @param key
     * @return
     */
    public synchronized V get(K key) {
        slowly();
        return map.get(key);
    }

    /**
     * 模拟耗时的操作
     */
    private void slowly() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟非常耗时的操作
     */
    private void verySlowly() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
