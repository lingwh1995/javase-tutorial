package headfirst.designpatterns.iterator.dinermerger;

/**
 * 数组迭代器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ArrayIterator implements Iterator {

    MenuItem[] items;
    int position = 0;

    public ArrayIterator(MenuItem[] items) {
        this.items = items;
    }

    public MenuItem next() {
        MenuItem menuItem = items[position];
        position = position + 1;
        return menuItem;
    }

    public boolean hasNext() {
        if (position >= items.length || items[position] == null) {
            return false;
        } else {
            return true;
        }
    }
}
