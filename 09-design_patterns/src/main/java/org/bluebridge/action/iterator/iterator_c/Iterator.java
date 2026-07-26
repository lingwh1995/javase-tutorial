package org.bluebridge.action.iterator.iterator_c;

/**
 * 迭代器接口
 *
 * @author lingwh
 * @date 2023/12/7 12:45
 */
public interface Iterator {

    boolean hasNext();

    Object next();

    boolean remove();
}
