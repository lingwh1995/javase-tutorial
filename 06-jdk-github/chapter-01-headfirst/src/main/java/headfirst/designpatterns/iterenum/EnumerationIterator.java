package headfirst.designpatterns.iterenum;

import java.util.*;

/**
 * 枚举转迭代器适配器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
