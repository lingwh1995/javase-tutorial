package headfirst.designpatterns.iterenum;

import java.util.*;

/**
 * @author lingwh
 * @desc 枚举转迭代器适配器
 * @date 2026/7/9 00:00
 */
public class EnumerationIterator implements Iterator<Object> {
    Enumeration<?> enumeration;

    public EnumerationIterator(Enumeration<?> enumeration) {
        this.enumeration = enumeration;
    }

    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    public Object next() {
        return enumeration.nextElement();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
