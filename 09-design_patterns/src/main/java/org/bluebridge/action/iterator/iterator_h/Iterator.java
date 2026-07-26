package org.bluebridge.action.iterator.iterator_h;

/**
 * 迭代器接口，定义访问和遍历元素的操作，实现双向迭代
 *
 * @author lingwh
 * @date 2019/8/20 13:16
 */
public interface Iterator {

    void first();

    void next();

    boolean isDone();

    Object currentItem();

    /**
     * 判断是否为第一个元素
     *
     * @return 如果为第一个元素，返回 true，否则返回 false
     */
    boolean isFirst();

    /**
     * 移动到聚合对象的上一个位置
     */
    void previous();
}
