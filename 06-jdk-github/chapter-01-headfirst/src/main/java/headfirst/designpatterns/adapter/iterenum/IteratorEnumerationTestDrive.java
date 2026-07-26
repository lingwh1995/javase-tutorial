package headfirst.designpatterns.adapter.iterenum;

import java.util.*;

/**
 * 迭代器转枚举测试驱动类
 *
 * @author lingwh
 * @date 2023/12/7 08:15
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
