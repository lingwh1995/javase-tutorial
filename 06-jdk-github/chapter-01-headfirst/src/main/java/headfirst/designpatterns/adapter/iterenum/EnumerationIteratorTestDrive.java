package headfirst.designpatterns.adapter.iterenum;

import java.util.*;

/**
 * @author lingwh
 * @desc 枚举转迭代器测试驱动类
 * @date 2026/7/9 00:00
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
