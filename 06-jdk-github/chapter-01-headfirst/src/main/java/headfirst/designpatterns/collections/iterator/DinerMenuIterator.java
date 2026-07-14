package headfirst.designpatterns.collections.iterator;

/**
 * 餐厅菜单迭代器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DinerMenuIterator implements Iterator {

    String[] items;
    int position = 0;

    public DinerMenuIterator(String[] items) {
        this.items = items;
    }

    public String next() {
        String menuItem = items[position];
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
