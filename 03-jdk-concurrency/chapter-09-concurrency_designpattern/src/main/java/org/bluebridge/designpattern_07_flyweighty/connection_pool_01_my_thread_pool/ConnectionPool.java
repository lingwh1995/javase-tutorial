package org.bluebridge.designpattern_07_flyweighty.connection_pool_01_my_thread_pool;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author lingwh
 * @desc 自定义线程池
 * @date 2026/7/9 00:00
 */
public class ConnectionPool {
    // 1. 连接池大小
    private final int poolSize;

    // 2. 连接对象数组
    private Connection[] connections;

    // 3. 连接状态数组 0 表示空闲， 1 表示繁忙
    private AtomicIntegerArray states;

    // 4. 构造方法初始化
    public ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.connections = new Connection[poolSize];
        this.states = new AtomicIntegerArray(new int[poolSize]);
        for (int i = 0; i < poolSize; i++) {
            connections[i] = new MyConnection("连接" + (i + 1));
        }
    }

    // 5. 借连接
    public Connection borrow() {
        synchronized (this) {
            while (true) {
                for (int i = 0; i < poolSize; i++) {
                    // 获取空闲连接
                    if (states.get(i) == 0) {
                        if (states.compareAndSet(i, 0, 1)) {
                            System.out.println(
                                    Thread.currentThread().getName() + " borrow " + connections[i] + "......");
                            return connections[i];
                        }
                    }
                }
                // 如果没有空闲连接，当前线程进入等待
                try {
                    System.out.println(Thread.currentThread().getName() + " wait......");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 6. 归还连接
    public void free(Connection conn) {
        synchronized (this) {
            for (int i = 0; i < poolSize; i++) {
                if (connections[i] == conn) {
                    states.set(i, 0);
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + " free " + conn + "......");
            this.notifyAll();
        }
    }
}
