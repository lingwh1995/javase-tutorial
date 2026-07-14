package headfirst.designpatterns.iterenum;

import java.util.*;

/**
 * 枚举转迭代器测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class EnumerationIteratorTestDrive {

    public static void main(String args[]) {
        Vector<String> v = new Vector<String>(Arrays.asList(args));
        Iterator<?> iterator = new EnumerationIterator(v.elements());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
