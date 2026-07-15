package og.bluebridge.generic.chapter_07_generic_wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符测试
 *
 * @author lingwh
 * @date 2026/7/8 18:35
 */
public class GenericWildcardTest {

    public static void main(String[] args) {
        System.out.println("--- 1. 测试无界通配符 <?> ---");
        List<String> strList = List.of("Java", "Go", "Python");
        List<Integer> intList = List.of(100, 200, 300);
        printList(strList);
        printList(intList);

        System.out.println("\n--- 2. 测试上界通配符 <? extends T> ---");
        List<Integer> myInts = List.of(10, 20);
        List<Double> myDoubles = List.of(1.5, 2.5);
        // 计算总和
        System.out.println("Integer Sum: " + calculateSum(myInts));
        System.out.println("Double Sum: " + calculateSum(myDoubles));

        System.out.println("\n--- 3. 测试下界通配符 <? super T> ---");
        List<Number> numberList = new ArrayList<>(); // 必须是可变列表，因为要写入
        List<Object> objectList = new ArrayList<>();

        fillList(numberList);
        fillList(objectList);

        System.out.println("Number List after fill: " + numberList);
        System.out.println("Object List after fill: " + objectList);
    }

    /**
     * 1. 无界通配符 <?> 适用场景：只读，不关心具体类型，或者使用 Object 类方法。
     */
    public static void printList(List<?> list) {
        System.out.print("List Content: ");
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    /**
     * 2. 上界通配符 <? extends Number> 适用场景：只读（Producer）。 可以安全地读取为 Number，但不能写入（除了 null）。
     */
    public static double calculateSum(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        // list.add(10); // 编译错误！无法确定 list 具体是 Integer 还是 Double 类型
        return sum;
    }

    /**
     * 3. 下界通配符 <? super Integer> 适用场景：只写（Consumer）。 可以安全地写入 Integer 及其子类，读取时只能当做 Object。
     */
    public static void fillList(List<? super Integer> list) {
        list.add(10); // OK
        list.add(999); // OK

        // Integer i = list.get(0); // 编译错误！不确定取出来的是 Integer 还是 Number/Object
        // Object obj = list.get(0); // OK
    }
}
