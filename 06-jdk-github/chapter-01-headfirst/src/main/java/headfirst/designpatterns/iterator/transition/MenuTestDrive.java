package headfirst.designpatterns.iterator.transition;

import java.util.ArrayList;

/**
 * 菜单测试驱动
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
