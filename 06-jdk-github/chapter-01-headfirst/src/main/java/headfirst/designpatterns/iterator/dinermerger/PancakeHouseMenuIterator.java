package headfirst.designpatterns.iterator.dinermerger;

import java.util.ArrayList;

/**
 * 煎饼屋菜单迭代器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PancakeHouseMenuIterator implements Iterator {

    ArrayList<MenuItem> items;
    int position = 0;

    public PancakeHouseMenuIterator(ArrayList<MenuItem> items) {
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
