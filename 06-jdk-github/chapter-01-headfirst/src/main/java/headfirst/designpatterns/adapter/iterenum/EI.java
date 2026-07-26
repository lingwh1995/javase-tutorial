package headfirst.designpatterns.adapter.iterenum;

import java.util.*;

/**
 * 枚举与迭代器示例
 *
 * @author lingwh
 * @date 2023/12/7 12:48
 */
public class EI {

    public static void main(String args[]) {
        Vector<String> v = new Vector<String>(Arrays.asList(args));
        Enumeration<String> enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
        Iterator<String> iterator = v.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
