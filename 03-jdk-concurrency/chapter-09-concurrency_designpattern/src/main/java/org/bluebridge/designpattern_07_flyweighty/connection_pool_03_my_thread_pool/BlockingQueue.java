package org.bluebridge.designpattern_07_flyweighty.connection_pool_03_my_thread_pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class BlockingQueue<T> {

    // 阻塞队列，存放任务
    private Deque<T> queue = new ArrayDeque<>();
    // 队列的最大容量
    private int capacity;
    // 锁
    private ReentrantLock lock = new ReentrantLock();
    // 生产者条件变量
    private Condition fullWaitSet = lock.newCondition();
    // 消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    /**
     * 构造方法
     *
     * @param capacity
     */
    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 超时阻塞获取
     *
     * @param timeout
     * @param unit
     * @return
     */
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        // 将时间转换为纳秒
        long nanoTime = unit.toNanos(timeout);
        try {
            while (queue.size() == 0) {
                try {
                    // 等待超时依旧没有获取，返回null
                    if (nanoTime <= 0) {
                        return null;
                    }
                    // 该方法返回的是剩余时间
                    nanoTime = emptyWaitSet.awaitNanos(nanoTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.pollFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞获取
     *
     * @return
     */
    public T take() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.pollFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞添加
     *
     * @param t
     */
    public void put(T t) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    System.out.println(Thread.currentThread().toString() + "等待加入任务队列:" + t.toString());
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().toString() + "加入任务队列:" + t.toString());
            queue.addLast(t);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 超时阻塞添加
     *
     * @param t
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean offer(T t, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanoTime = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    if (nanoTime <= 0) {
                        System.out.println("等待超时，加入失败：" + t);
                        return false;
                    }
                    System.out.println(Thread.currentThread().toString() + "等待加入任务队列:" + t.toString());
                    nanoTime = fullWaitSet.awaitNanos(nanoTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().toString() + "加入任务队列:" + t.toString());
            queue.addLast(t);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
