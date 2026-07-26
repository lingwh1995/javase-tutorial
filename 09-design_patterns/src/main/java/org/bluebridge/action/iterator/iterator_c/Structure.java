package org.bluebridge.action.iterator.iterator_c;

/**
 * 结构接口
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public interface Structure {

    String getName();

    void addElement(String string);

    Iterator iterator();
}
