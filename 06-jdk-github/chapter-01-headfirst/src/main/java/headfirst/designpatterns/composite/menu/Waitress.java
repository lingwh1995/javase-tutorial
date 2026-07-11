package headfirst.designpatterns.composite.menu;

/**
 * @author lingwh
 * @desc 女服务员
 * @date 2026/7/9 00:00
 */
public class Waitress {
    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();
    }
}
