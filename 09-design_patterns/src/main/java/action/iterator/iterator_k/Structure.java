package action.iterator.iterator_k;

import java.util.Iterator;

/**
 * @author lingwh
 * @desc 结构接口
 * @date 2026/7/9 00:00
 */
public interface Structure {
    void addElement(Person person);

    Object getElement(int index);

    int size();

    Iterator iterator();
}
