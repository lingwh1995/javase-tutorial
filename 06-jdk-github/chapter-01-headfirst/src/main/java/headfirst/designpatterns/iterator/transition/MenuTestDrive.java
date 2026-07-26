package headfirst.designpatterns.iterator.transition;

import java.util.ArrayList;

/**
 * 菜单测试驱动
 *
 * @author lingwh
 * @date 2023/12/7 21:15
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
