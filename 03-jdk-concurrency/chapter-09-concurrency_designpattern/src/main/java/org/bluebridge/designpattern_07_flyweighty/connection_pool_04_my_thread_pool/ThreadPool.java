package org.bluebridge.designpattern_07_flyweighty.connection_pool_04_my_thread_pool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc 自定义线程池
 * @date 2026/7/9 00:00
 */
public class ThreadPool {
    // 阻塞队列
    BlockingQueue<Runnable> taskQueue;
    // 线程集合
    Set<Worker> workers = new HashSet<>();
    // 拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;
    // 构造方法
    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy){
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
        taskQueue = new BlockingQueue<Runnable>(queueCapacity);
    }

    // 线程数
    private int coreSize;
    // 任务超时时间
    private long timeout;
    // 时间单元
    private TimeUnit timeUnit;

    // 线程池的执行方法
    public void execute(Runnable task) {
        // 当线程数大于等于coreSize的时候，将任务放入阻塞队列
        // 当线程数小于coreSize的时候，新建一个Worker放入workers
        // 注意workers类不是线程安全的， 需要加锁
        synchronized (workers) {
            if (workers.size() >= coreSize) {
//                taskQue.put(task);
                // 死等
                // 带超时等待
                // 让调用者放弃执行任务
                // 让调用者抛出异常
                // 让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy, task);
            } else {
                Worker worker = new Worker(task);
                System.out.println(Thread.currentThread().toString() + "新增worker:" + worker + ",task:" + task);
                workers.add(worker);
                worker.start();
            }
        }
    }

    // 工作类
    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 巧妙的判断
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    System.out.println(Thread.currentThread().toString() + "正在执行:" + task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                System.out.println(Thread.currentThread().toString() + "worker被移除:" + this.toString());
                workers.remove(this);
            }
        }
    }
}
