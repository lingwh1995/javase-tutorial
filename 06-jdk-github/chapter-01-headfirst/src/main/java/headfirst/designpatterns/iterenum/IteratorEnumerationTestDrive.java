package headfirst.designpatterns.iterenum;

import java.util.*;

/**
 * 迭代器转枚举测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class IteratorEnumerationTestDrive {

    public static void main(String args[]) {
        ArrayList<String> l = new ArrayList<String>(Arrays.asList(args));
        Enumeration<?> enumeration = new IteratorEnumeration(l.iterator());
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }
}
