package headfirst.designpatterns.iterenum;

import java.util.*;

/**
 * 迭代器转枚举适配器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
