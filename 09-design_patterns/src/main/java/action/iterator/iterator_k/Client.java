package action.iterator.iterator_k;

import java.util.Iterator;

/**
 * @author lingwh
 * @desc 客户端测试
 * @date 2019/9/23 10:47
 */
public class Client {
    public static void main(String[] args) {
        ListStructure listStructure = new ListStructure();
        listStructure.addElement(new Person("zs", 18));
        listStructure.addElement(new Person("ls", 28));
        listStructure.addElement(new Person("ww", 38));
        Iterator iterator = listStructure.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
