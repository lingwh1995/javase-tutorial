package org.bluebridge.action.iterator.iterator_c;

/**
 * 对于 List 和 Array 两种不同的结构，可以使用相同的方式来遍历
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class Client {

    public static void main(String[] args) {
        /**
         * 遍历 list 结构
         */
        Structure listStructure = new ListStructure();
        Iterator listiIterator = listStructure.iterator();
        while (listiIterator.hasNext()) {
            System.out.println(listiIterator.next());
        }

        System.out.println("------------------");
        /**
         * 遍历 Array 结构
         */
        Structure arrayStructure = new ArrayStructure();
        Iterator arrayIterator = arrayStructure.iterator();
        while (arrayIterator.hasNext()) {
            System.out.println(arrayIterator.next());
        }
    }
}
