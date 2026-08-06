package org.bluebridge.section_05_jdk5.unit_01_generic;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JDK1.5 泛型测试
 *
 * 泛型(Generic)是 JDK1.5 引入的最重要的特性之一, 它提供了编译时类型安全检测机制,
 * 允许在定义类、接口、方法时使用类型参数, 在使用时指定具体类型:
 * 1. 泛型类: 在类名后使用 <T> 声明类型参数, 如 List<T>
 * 2. 泛型接口: 在接口名后使用 <T> 声明类型参数, 如 Comparator<T>
 * 3. 泛型方法: 在方法返回值前使用 <T> 声明类型参数, 与所属类是否是泛型类无关
 * 4. 泛型通配符: ? 表示未知类型; ? extends T 表示 T 或 T 的子类(上界); ? super T 表示 T 或 T 的父类(下界)
 * 5. 类型擦除: 泛型信息只存在于编译期, 运行时会被擦除为 Object(无上界时)或上界类型
 *
 * @author lingwh
 * @date 2026/08/05 18:26
 */
public class GenericTest {

    /**
     * 泛型类: 定义一个盒子, 可以存放任意类型的对象
     *
     * @param <T> 盒子里存放的对象的类型
     */
    static class Box<T> {
        private T item;

        public void setItem(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }

    /**
     * 泛型接口: 定义一个数据处理器接口
     *
     * @param <T> 处理的数据的类型
     */
    interface Processor<T> {
        void process(T data);

        T getResult();
    }

    /**
     * 泛型接口实现类: 在实现时指定具体类型参数为 String
     */
    static class StringProcessor implements Processor<String> {
        private String result;

        @Override
        public void process(String data) {
            result = data + " 处理完成";
        }

        @Override
        public String getResult() {
            return result;
        }
    }

    /**
     * 泛型方法: 打印数组中的所有元素
     *
     * @param array 任意类型的数组
     */
    public <E> void printArray(E[] array) {
        System.out.print("数组元素: ");
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * 泛型方法: 返回两个参数中较大的一个(类型参数带 Comparable 上界约束)
     *
     * @param a 参与比较的参数一
     * @param b 参与比较的参数二
     * @return 较大的参数
     */
    public <T extends Comparable<T>> T getMax(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    /**
     * 泛型方法: 返回任意类型对象的描述信息
     *
     * @param value 任意类型的对象
     * @return 对象的描述信息
     */
    public <T> String describe(T value) {
        return "类型: " + value.getClass().getSimpleName() + ", 值: " + value;
    }

    /**
     * 测试泛型类: 同一个泛型类可以存放不同类型的数据
     */
    @Test
    public void testGenericClass() {
        // 使用泛型类存放字符串
        Box<String> stringBox = new Box<>();
        stringBox.setItem("hello");
        System.out.println("stringBox.getItem(): " + stringBox.getItem());
        // 使用泛型类存放整数
        Box<Integer> integerBox = new Box<>();
        integerBox.setItem(100);
        System.out.println("integerBox.getItem(): " + integerBox.getItem());
        // 编译期类型安全: 下面的代码无法通过编译(integerBox 只能存放 Integer)
        // integerBox.setItem("world");
    }

    /**
     * 测试泛型接口: 实现泛型接口并指定具体类型
     */
    @Test
    public void testGenericInterface() {
        Processor<String> processor = new StringProcessor();
        processor.process("任务");
        System.out.println(processor.getResult());
    }

    /**
     * 测试泛型方法: 泛型方法不依赖于泛型类, 可以独立使用
     */
    @Test
    public void testGenericMethod() {
        // 泛型方法打印不同类型的数组
        String[] strArray = {"a", "b", "c"};
        printArray(strArray);
        Integer[] intArray = {1, 2, 3};
        printArray(intArray);
        // 泛型方法返回最大值(类型参数带 Comparable 上界)
        System.out.println("整数最大值: " + getMax(3, 5));
        System.out.println("字符串最大值: " + getMax("abc", "abd"));
        // 泛型方法描述任意对象
        System.out.println(describe(123));
        System.out.println(describe("hello"));
    }

    /**
     * 测试泛型通配符 ?: 表示未知类型, 可以接收任意类型的泛型对象
     */
    @Test
    public void testWildcard() {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("a", "b");
        // ? 通配符可以接收任意类型的 List
        printList(intList);
        printList(strList);
    }

    /**
     * 辅助方法: 使用 ? 通配符接收任意类型的集合
     *
     * @param list 任意类型的集合
     */
    private void printList(List<?> list) {
        System.out.println("集合内容: " + list);
    }

    /**
     * 测试上界通配符 ? extends T: 可以读取, 但不能写入(除了 null)
     */
    @Test
    public void testUpperBoundWildcard() {
        // ? extends Number 可以接收 Number 及其子类的集合
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.5, 2.5);
        List<Number> numberList = new ArrayList<>();
        numberList.add(10);
        numberList.add(20);
        sum(intList);
        sum(doubleList);
        sum(numberList);
        // 读取: 上界通配符读出来的元素类型是上界类型 Number
        Number first = intList.get(0);
        System.out.println("读取到的元素(编译期类型为 Number): " + first);
        // 写入: 上界通配符的集合无法写入元素(具体类型不确定), 下面的代码无法通过编译
        // List<? extends Number> list = new ArrayList<Integer>();
        // list.add(100);
    }

    /**
     * 辅助方法: 计算集合中所有数字的总和(上界通配符)
     *
     * @param list Number 及其子类型的集合
     * @return 总和
     */
    private double sum(List<? extends Number> list) {
        double total = 0;
        for (Number num : list) {
            total += num.doubleValue();
        }
        System.out.println("总和: " + total);
        return total;
    }

    /**
     * 测试下界通配符 ? super T: 可以写入 T 及其子类, 读取时编译期类型是 Object
     */
    @Test
    public void testLowerBoundWildcard() {
        // ? super Integer 可以接收 Integer 及其父类(Number、Object)的集合
        List<Object> objectList = new ArrayList<>();
        addIntegers(objectList);
        System.out.println("Object 集合内容: " + objectList);
        // 写入: 下界通配符的集合可以写入 Integer 及其子类
        List<? super Integer> list = objectList;
        list.add(100);
        System.out.println("写入后的集合内容: " + list);
        // 读取: 下界通配符读出来的元素只能当作 Object 处理
        Object element = list.get(0);
        System.out.println("读取到的元素(编译期类型为 Object): " + element);
    }

    /**
     * 辅助方法: 向集合中添加整数(下界通配符)
     *
     * @param list Integer 及其父类型的集合
     */
    private void addIntegers(List<? super Integer> list) {
        for (int i = 1; i <= 3; i++) {
            list.add(i);
        }
    }

    /**
     * 测试类型擦除: 泛型信息在运行时被擦除, 无法获取具体的类型参数
     */
    @Test
    public void testTypeErasure() {
        Box<String> stringBox = new Box<>();
        Box<Integer> integerBox = new Box<>();
        // 运行时两个对象是同一个类, 说明泛型信息已被擦除
        System.out.println("stringBox 的类: " + stringBox.getClass().getName());
        System.out.println("integerBox 的类: " + integerBox.getClass().getName());
        System.out.println("两个对象运行时类型是否相同: " + (stringBox.getClass() == integerBox.getClass()));
        // 通过反射查看泛型字段的运行时类型: 无上界时擦除为 Object
        Field[] fields = Box.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("字段 " + field.getName() + " 的运行时类型: " + field.getType().getName());
        }
        // 说明: 类型参数 T 在编译期(类型检查结束后)会被擦除, 替换为 Object 或上界类型,
        // 因此运行时无法通过反射直接获取 T 的具体类型, 这就是泛型的类型擦除机制
    }
}
