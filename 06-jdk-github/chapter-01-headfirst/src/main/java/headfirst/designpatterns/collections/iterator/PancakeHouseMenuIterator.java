package headfirst.designpatterns.collections.iterator;

import java.util.ArrayList;

/**
 * @author lingwh
 * @desc 煎饼屋菜单迭代器
 * @date 2026/7/9 00:00
 */
public class PancakeHouseMenuIterator implements Iterator {
    ArrayList<String> items;
    int position = 0;

    public PancakeHouseMenuIterator(ArrayList<String> items) {
        this.items = items;
    }

    public String next() {
        String menuItem = (String) items.get(position);
        position = position + 1;
        return menuItem;
    }

    public boolean hasNext() {
        if (position >= items.size()) {
            return false;
        } else {
            return true;
        }
    }
}
