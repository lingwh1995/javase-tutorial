package org.bluebridge.designpattern_07_flyweighty.connection_pool_04_my_thread_pool;

/**
 * 拒绝策略
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@FunctionalInterface
interface RejectPolicy<T> {

    void reject(BlockingQueue<T> queue, T task);
}
