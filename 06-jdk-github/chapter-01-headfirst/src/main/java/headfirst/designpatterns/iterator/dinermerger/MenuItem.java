package headfirst.designpatterns.iterator.dinermerger;

/**
 * 菜单项
 *
 * @author lingwh
 * @date 2023/12/7 11:16
 */
public class MenuItem {

    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public String toString() {
        return (name + ", $" + price + "\n   " + description);
    }
}
