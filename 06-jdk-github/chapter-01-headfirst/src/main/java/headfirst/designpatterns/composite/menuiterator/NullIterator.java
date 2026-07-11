package headfirst.designpatterns.composite.menuiterator;

import java.util.Iterator;

/**
 * @author lingwh
 * @desc 空迭代器
 * @date 2026/7/9 00:00
 */
public class NullIterator implements Iterator<MenuComponent> {

    public MenuComponent next() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }

    /*
     * No longer needed as of Java 8
     *
     * (non-Javadoc)
     * @see java.util.Iterator#remove()
     *
    public void remove() {
        throw new UnsupportedOperationException();
    }
    */
}
