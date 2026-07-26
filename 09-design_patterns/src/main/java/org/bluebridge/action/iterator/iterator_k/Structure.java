package org.bluebridge.action.iterator.iterator_k;

import java.util.Iterator;

/**
 * 结构接口
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public interface Structure {

    void addElement(Person person);

    Object getElement(int index);

    int size();

    Iterator iterator();
}
