package headfirst.designpatterns.iterator.dinermerger;

/**
 * @author lingwh
 * @desc 数组迭代器
 * @date 2026/7/9 00:00
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
