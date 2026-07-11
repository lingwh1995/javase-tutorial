package headfirst.designpatterns.collections.iterator_builtin;

import java.util.*;

/**
 * @author lingwh
 * @desc 咖啡馆菜单测试类
 * @date 2026/7/9 00:00
 */
public class Cafe {

    public static void main(String args[]) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();

        // with iterators
        Iterator<String> pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator<String> dinerIterator = dinerMenu.createIterator();

        System.out.println("\nMENU (with iterators)\n----\nBREAKFAST");
        printMenu(pancakeIterator);
        System.out.println("\nLUNCH");
        printMenu(dinerIterator);
    }

    private static void printMenu(Iterator<String> iterator) {
        while (iterator.hasNext()) {
            String menuItem = (String) iterator.next();
            System.out.println(menuItem);
        }
    }
}
