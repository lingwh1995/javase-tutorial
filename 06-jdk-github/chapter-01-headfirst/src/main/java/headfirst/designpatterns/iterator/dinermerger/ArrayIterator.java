package headfirst.designpatterns.iterator.dinermerger;

/**
 * 数组迭代器
 *
 * @author lingwh
 * @date 2023/12/7 11:34
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
