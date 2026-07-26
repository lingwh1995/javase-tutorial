package headfirst.designpatterns.collections.iterator;

import java.util.ArrayList;

/**
 * 煎饼屋菜单实现类
 *
 * @author lingwh
 * @date 2023/12/7 14:09
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

    @Override
    public Iterator createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }

    @Override
    public String toString() {
        return "Pancake House Menu";
    }

    // other menu methods here
}
