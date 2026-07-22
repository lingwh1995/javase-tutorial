package org.bluebridge.thread.section_03_thread_designpattern.threadlocal_storage.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 模拟器
 *
 * @author lingwh
 * @date 2026/7/13 16:29
 */
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (value == null) {
                return initValue();
            }
            return value;
        }
    }

    private T initValue() {
        return null;
    }
}
