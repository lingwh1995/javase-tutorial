package org.bluebridge.api._comparator;

import java.util.*;
import org.junit.Test;

/**
 * Comparator外部比较器测试
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class ComparatorTest {

    /**
     * 测试使用外部比较器接口Comparator对集合进行排序
     */
    @Test
    public void testCollectionComparable() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("a", 10));
        personList.add(new Person("b", 20));
        personList.add(new Person("c", 30));

        // 打印list的原始序列
        System.out.printf("原始序列：%s\n", personList);

        //使用外部比较器接口Comparator进行排序
        Collections.sort(personList,new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.printf("按年龄升序排序(使用外部比较器接口Comparator进行排序)： %s\n",personList);

        Collections.sort(personList,new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        System.out.printf("按年龄降序排序(使用外部比较器接口Comparator进行排序)： %s\n",personList);
    }

    /**
     * 测试使用外部比较器接口Comparator对数组进行排序
     */
    @Test
    public void testArrayComparable() {
        Person[] personArr = new Person[3];
        personArr[0] = new Person("a", 10);
        personArr[1] = new Person("b", 20);
        personArr[2] = new Person("c", 30);

        // 打印数组的原始序列
        System.out.printf("原始序列： %s\n", personArr);

        // 使用外部比较器接口Comparator进行排序
        Arrays.sort(personArr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.printf("按年龄升序排序(使用外部比较器接口Comparator进行排序)： %s\n",Arrays.toString(personArr));

        Arrays.sort(personArr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        System.out.printf("按年龄降序排序(使用外部比较器接口Comparator进行排序)： %s\n",Arrays.toString(personArr));
    }
}
