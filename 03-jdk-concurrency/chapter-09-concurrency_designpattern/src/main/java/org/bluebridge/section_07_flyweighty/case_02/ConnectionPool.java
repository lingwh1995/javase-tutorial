package org.bluebridge.section_07_flyweighty.case_02;

import java.sql.Connection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 自定义线程池
 *
 * @author lingwh
 * @date 2025/3/20 14:57
 */
public class ConnectionPool {

    // 1. 连接池大小
    private final int size;
    // 2. 连接对象数组
    private Connection[] connections;
    // 3. 连接状态数组 0：空闲 1：忙碌
    private AtomicIntegerArray status;
    // 信号量
    private Semaphore signal;

    // 4. 构造器初始化
    ConnectionPool(int size) {
        this.size = size;
        connections = new Connection[size];
        status = new AtomicIntegerArray(new int[size]);
        signal = new Semaphore(size);
        for (int i = 0; i < size; i++) {
            connections[i] = new MyConnection("连接" + (i + 1));
        }
    }

    // 5. 借连接
    public Connection borrow() {
        while (true) {
            // 信号量加 1
            try {
                signal.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 成功，获取空闲连接
            for (int i = 0; i < size; i++) {
                if (status.get(i) == 0) {
                    if (status.compareAndSet(i, 0, 1)) {
                        System.out.printf("获得连接: %d\n", i);
                        return connections[i];
                    }
                }
            }
        }
    }

    // 6. 归还连接
    public void free(Connection connection) {
        for (int i = 0; i < size; i++) {
            if (connections[i] == connection) {
                System.out.printf("释放连接: %d\n", i);
                status.set(i, 0);
                // 信号量减 1
                signal.release();
                break;
            }
        }
    }
}
