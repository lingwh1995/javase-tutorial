package org.bluebridge.section_04_set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * TreeSet 有序且唯一排序测试
 *
 * 1. TreeSet(有序且唯一)，能对元素按照某种规则进行排序，底层数据结构是红黑树
 * 2. 排序有两种：
 *    - 自然排序(元素具备比较性)：需要实现 Comparable<T> 并重写 compareTo() 方法，与 hashCode() 和 equals() 无关，不需要重写这两个方法
 *    - 比较器排序(集合具备比较性)：在 TreeSet 的构造方法中传入 Comparator 的子类
 *
 * @author lingwh
 * @date 2019/7/9 10:30
 */
@Slf4j
public class TreeSetTest {

    @Test
    public void testTreeSet() {
        // 自然排序：存储 Integer
        // 自然排序(Comparator)：存储 Integer
        TreeSet<Integer> integers = new TreeSet<>();
        integers.add(10);
        integers.add(1);
        integers.add(1);
        integers.add(89);
        integers.add(13);
        log.debug("integers: {}", integers);

        System.out.println("-------------------------");
        // 比较器排序(Comparable)：存储自定义对象
        TreeSet<Dog> dogs = new TreeSet<>();
        dogs.add(new Dog("zs", 15));
        dogs.add(new Dog("ls", 16));
        dogs.add(new Dog("ww", 18));
        dogs.add(new Dog("zl", 11));
        dogs.add(new Dog("zs", 15));
        for (Dog dog : dogs) {
            log.debug("dog: {}", dog);
        }

        System.out.println("-------------------------");
        // 自然排序(Comparator)：存储自定义对象
        TreeSet<Cat> cats = new TreeSet<>(new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                // 主要条件
                int age = o2.age - o1.age;
                // 次要条件
                return age == 0 ? o1.name.compareTo(o2.name) : age;
            }
        });
        cats.add(new Cat("zs", 18));
        cats.add(new Cat("aa", 18));
        cats.add(new Cat("bb", 18));
        cats.add(new Cat("ls", 19));
        cats.add(new Cat("ww", 20));
        cats.add(new Cat("zs", 21));
        cats.add(new Cat("zs", 18));
        for (Cat cat : cats) {
            log.debug("cat: {}", cat);
        }
    }
}

@Data
@AllArgsConstructor
class Dog implements Comparable<Dog> {

    private String name;
    private Integer age;

    @Override
    public int compareTo(Dog dog) {
        if (this.name == dog.name && this.age == dog.age) {
            return 0;
        }
        return this.age > dog.age ? 1 : -1;
    }
}

@Data
@AllArgsConstructor
class Cat {

    protected String name;
    protected Integer age;
}
