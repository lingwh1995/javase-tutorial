package headfirst.designpatterns.iterator.dinermerger;

import java.util.ArrayList;

/**
 * @author lingwh
 * @desc 数组列表迭代器
 * @date 2026/7/9 00:00
 */
public class ArrayListIterator implements Iterator {

    ArrayList<MenuItem> items;
    int position = 0;

    public ArrayListIterator(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public MenuItem next() {
        MenuItem item = items.get(position);
        position = position + 1;
        return item;
    }

    public boolean hasNext() {
        if (position >= items.size()) {
            return false;
        } else {
            return true;
        }
    }
}
