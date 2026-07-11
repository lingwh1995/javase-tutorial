package headfirst.designpatterns.iterenum;

import java.util.*;

/**
 * @author lingwh
 * @desc 枚举与迭代器测试
 * @date 2026/7/9 00:00
 */
public class EI {
    public static void main(String args[]) {
        Vector<String> v = new Vector<String>(Arrays.asList(args));

        System.out.println("Using enumeration with Vector");
        Enumeration<String> enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        System.out.println("Using iterator with Vector");
        Iterator<String> iterator = v.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Using for/in with array of Strings");
        for (String color : args) {
            System.out.println(color);
        }
    }
}
