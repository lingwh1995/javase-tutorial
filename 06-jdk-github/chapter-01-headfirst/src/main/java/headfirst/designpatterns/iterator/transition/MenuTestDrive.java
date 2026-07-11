package headfirst.designpatterns.iterator.transition;

import java.util.ArrayList;

/**
 * @author lingwh
 * @desc 菜单测试驱动
 * @date 2026/7/9 00:00
 */
public class MenuTestDrive {
    public static void main(String args[]) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
        ArrayList<Menu> menus = new ArrayList<Menu>();
        menus.add(pancakeHouseMenu);
        menus.add(dinerMenu);
        Waitress waitress = new Waitress(menus);
        waitress.printMenu();
    }
}
