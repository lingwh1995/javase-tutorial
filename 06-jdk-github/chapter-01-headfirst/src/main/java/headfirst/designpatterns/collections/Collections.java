package headfirst.designpatterns.collections;

import java.util.*;

/**
 * 集合枚举迭代器示例
 *
 * @author lingwh
 * @date 2023/12/7 08:22
 */
public class Collections {

    public static void main(String args[]) {
        Vector<String> v = new Vector<String>(Arrays.asList(args));

        System.out.println("Using enumeration with Vector");
        Enumeration<String> enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        System.out.println("Using iterator with Vector");
        Iterator<String> iterator = (Iterator<String>) v.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Using for/in with array of Strings");
        for (String color : args) {
            System.out.println(color);
        }
    }
}
