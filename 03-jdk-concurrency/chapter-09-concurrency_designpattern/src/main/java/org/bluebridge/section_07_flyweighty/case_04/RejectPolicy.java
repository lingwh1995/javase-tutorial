package org.bluebridge.section_07_flyweighty.case_04;

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
