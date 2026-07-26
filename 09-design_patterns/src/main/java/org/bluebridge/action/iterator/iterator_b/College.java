package org.bluebridge.action.iterator.iterator_b;

/**
 * 学院接口
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public interface College {

    String getName();

    /**
     * 增加系的方法
     */
    void addDepartment(String name, String desc);

    /**
     * 返回一个迭代器，遍历
     */
    Iterator ceateIterator();
}
