package org.bluebridge.thread.section_03_thread_designpattern.workerthread;

import java.util.Arrays;

/**
 * Worker Thread 模式 - 通道
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Channel {

    private static final int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private int head;
    private int tail;
    private int count;

    private final WorkerThread[] workerPool;

    public Channel(int size) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[size];
        this.init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    /**
     * push switch to start all of worker to work.
     */
    public void startWorker() {
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    public synchronized void put(Request request) {
        while (count >= requestQueue.length) {
            try {
                this.wait();
            } catch (Exception e) {

            }
        }
        this.requestQueue[tail] = request;
        this.tail = (tail + 1) % requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = this.requestQueue[head];
        this.head = (this.head + 1) % this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
