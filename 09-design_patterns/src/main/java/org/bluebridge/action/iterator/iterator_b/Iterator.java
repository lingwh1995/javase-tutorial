package org.bluebridge.action.iterator.iterator_b;

/**
 * 迭代器接口
 *
 * @author lingwh
 * @date 2023/12/7 11:30
 */
public interface Iterator {

    boolean hasNext();

    Object next();

    boolean remove();
}
