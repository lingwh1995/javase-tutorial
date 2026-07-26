package org.bluebridge.section_07_flyweighty.case_04;

/**
 * 拒绝策略
 *
 * @author lingwh
 * @date 2025/3/25 11:07
 */
@FunctionalInterface
interface RejectPolicy<T> {

    void reject(BlockingQueue<T> queue, T task);
}
