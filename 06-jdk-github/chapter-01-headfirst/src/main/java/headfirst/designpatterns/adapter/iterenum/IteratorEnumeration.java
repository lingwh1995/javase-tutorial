package headfirst.designpatterns.adapter.iterenum;

import java.util.*;

/**
 * 迭代器转枚举适配器
 *
 * @author lingwh
 * @date 2023/12/7 09:23
 */
public class IteratorEnumeration implements Enumeration<Object> {

    Iterator<?> iterator;

    public IteratorEnumeration(Iterator<?> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public Object nextElement() {
        return iterator.next();
    }
}
