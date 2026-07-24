package org.bluebridge.section_05_map;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * TreeMap 存储对象并排序测试
 *
 * @author lingwh
 * @date 2019/7/9 10:30
 */
public class TreeMapTest {

    public static void main(String[] args) {
        // 自然排序
        Map<Student, String> treeMapComparable = new TreeMap<>();
        treeMapComparable.put(new Student("zs", 18), "张三");
        treeMapComparable.put(new Student("ww", 38), "王五");
        treeMapComparable.put(new Student("ls", 28), "李四");
        treeMapComparable.put(new Student("zs", 18), "张三");
        System.out.println(treeMapComparable);

        System.out.println("--------------------------------------------");
        // 比较器排序
        Map<Student, String> treeMapCmparor = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // 主要条件
                int age = s2.age - s1.age;
                return age == 0 ? s1.name.compareTo(s2.name) : age;
            }
        });
        treeMapCmparor.put(new Student("zs", 18), "张三");
        treeMapCmparor.put(new Student("aa", 18), "张三");
        treeMapCmparor.put(new Student("bb", 18), "张三");
        treeMapCmparor.put(new Student("ww", 38), "王五");
        treeMapCmparor.put(new Student("ls", 28), "李四");
        treeMapCmparor.put(new Student("zs", 18), "张三");
        System.out.println(treeMapCmparor);

        System.out.println("-------------------------------------");
        // 测试 TreeMap.put();
        Map<String, String> tree = new TreeMap<>();
        System.out.println(tree.put("first", "zs"));
        System.out.println(tree.put("first", "zs"));
    }
}

class Student implements Comparable<Student> {

    protected String name;
    protected Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student student) {
        if (this.name.equals(student.name) && this.age == student.age) {
            return 0;
        }
        return student.age - this.age;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", age='" + age + '\'' + '}';
    }
}

class Dog {

    private String name;
    private Integer age;

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
