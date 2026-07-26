package headfirst.designpatterns.composite.menu;

/**
 * 女服务员
 *
 * @author lingwh
 * @date 2023/12/7 12:53
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
