package headfirst.designpatterns.iterator.implicit;

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
        Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
        // Use implicit iteration
        waitress.printMenu();
    }
}
