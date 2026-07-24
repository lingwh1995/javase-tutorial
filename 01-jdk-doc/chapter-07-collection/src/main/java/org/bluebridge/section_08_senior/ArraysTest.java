package org.bluebridge.section_08_senior;

import java.util.Arrays;
import java.util.List;

/**
 * 数组工具类测试
 *
 * @author lingwh
 * @date 2026/6/12 10:30
 */
public class ArraysTest {

    public static void main(String[] args) {
        /**
         * public static <T> List<T> asList(T... a) {}
         * 把数组转换为集合，本质上还是数组，所以只要长度不发生改变，任何操作都可以
         */
        String[] s = { "a", "b", "c" };
        List<String> l1 = Arrays.asList(s);
        List<String> l2 = Arrays.asList("a,b,c");
        System.out.println(l2);
        System.out.println(l2);
        System.out.println(l2.size() + "---" + l2.get(0));

        Person zs = new Person("zs", "18");
        Person ls = new Person("ls", "19");
        Person ww = new Person("ww", "29");
        List<Person> pers = Arrays.asList(zs, ls, ww);
        System.out.println(pers);
    }
}

class Person {

    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Per{" + "name='" + name + '\'' + ", age='" + age + '\'' + '}';
    }
}
