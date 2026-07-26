package headfirst.designpatterns.iterator.implicit;

/**
 * 菜单测试驱动
 *
 * @author lingwh
 * @date 2023/12/7 09:30
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
