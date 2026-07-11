package headfirst.designpatterns.collections.iterator;

import java.util.ArrayList;

/**
 * @author lingwh
 * @desc 煎饼屋菜单实现类
 * @date 2026/7/9 00:00
 */
public class PancakeHouseMenu implements Menu {
    ArrayList<String> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<String>();

        addItem("K&B's Pancake Breakfast");
        addItem("Regular Pancake Breakfast");
        addItem("Blueberry Pancakes");
        addItem("Waffles");
    }

    public void addItem(String name) {
        menuItems.add(name);
    }

    public ArrayList<String> getMenuItems() {
        return menuItems;
    }

    public Iterator createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }

    public String toString() {
        return "Pancake House Menu";
    }

    // other menu methods here
}
