package org.bluebridge.action.iterator.iterator_b;

/**
 * 学院接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface College {

    String getName();

    /**
     * 增加系的方法
     */
    void addDepartment(String name, String desc);

    /**
     * 返回一个迭代器,遍历
     */
    Iterator ceateIterator();
}
