package headfirst.designpatterns.composite.menuiterator;

import java.util.Iterator;

/**
 * 空迭代器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
     * 
     * @see java.util.Iterator#remove()
     *
     * public void remove() {
     * throw new UnsupportedOperationException();
     * }
     */
}
