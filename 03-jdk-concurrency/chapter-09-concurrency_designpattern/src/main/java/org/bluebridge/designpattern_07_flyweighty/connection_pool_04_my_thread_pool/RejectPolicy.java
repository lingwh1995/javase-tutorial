package org.bluebridge.designpattern_07_flyweighty.connection_pool_04_my_thread_pool;

/**
 * @author lingwh
 * @desc 拒绝策略
 * @date 2026/7/9 00:00
 */
@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}
