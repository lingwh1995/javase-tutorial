package headfirst.designpatterns.composite.menu;

/**
 * 女服务员
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
