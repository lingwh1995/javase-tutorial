package headfirst.designpatterns.iterator.implicit;

/**
 * @author lingwh
 * @desc 菜单测试驱动
 * @date 2026/7/9 00:00
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
