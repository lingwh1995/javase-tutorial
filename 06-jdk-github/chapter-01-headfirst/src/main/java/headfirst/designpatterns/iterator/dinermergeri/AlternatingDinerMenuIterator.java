package headfirst.designpatterns.iterator.dinermergeri;

import java.util.Calendar;
import java.util.Iterator;

/**
 * @author lingwh
 * @desc 交替餐厅菜单迭代器
 * @date 2026/7/9 00:00
 */
public class AlternatingDinerMenuIterator implements Iterator<Object> {

    MenuItem[] items;
    int position;

    public AlternatingDinerMenuIterator(MenuItem[] items) {
        this.items = items;
        position = Calendar.DAY_OF_WEEK % 2;
    }

    public Object next() {
        MenuItem menuItem = items[position];
        position = position + 2;
        return menuItem;
    }

    public boolean hasNext() {
        if (position >= items.length || items[position] == null) {
            return false;
        } else {
            return true;
        }
    }

    public void remove() {
        throw new UnsupportedOperationException(
                "Alternating Diner Menu Iterator does not support remove()");
    }
}
