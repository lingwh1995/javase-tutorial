package headfirst.designpatterns.adapter.iterenum;

import java.util.*;

/**
 * 枚举转迭代器测试驱动类
 *
 * @author lingwh
 * @date 2023/12/7 10:07
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
