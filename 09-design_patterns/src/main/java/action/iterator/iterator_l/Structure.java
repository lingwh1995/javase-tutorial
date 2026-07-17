package action.iterator.iterator_l;

import java.util.Iterator;

/**
 * 结构接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Structure {

    void addElement(Person person);

    Object getElement(int index);

    int size();

    Iterator iterator();
}
