package headfirst.designpatterns.adapter.iterenum;

import java.util.*;

/**
 * 枚举转迭代器适配器
 *
 * @author lingwh
 * @date 2023/12/7 11:34
 */
public class EnumerationIterator implements Iterator<Object> {

    Enumeration<?> enumeration;

    public EnumerationIterator(Enumeration<?> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
