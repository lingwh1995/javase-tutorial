package headfirst.designpatterns.adapter.iterenum;

import java.util.*;

/**
 * @author lingwh
 * @desc 迭代器转枚举适配器
 * @date 2026/7/9 00:00
 */
public class IteratorEnumeration implements Enumeration<Object> {
    Iterator<?> iterator;

    public IteratorEnumeration(Iterator<?> iterator) {
        this.iterator = iterator;
    }

    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    public Object nextElement() {
        return iterator.next();
    }
}
