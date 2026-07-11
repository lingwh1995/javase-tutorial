package org.bluebridge.chapter_04_set;

import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * HashSet保证元素唯一性测试
 *
 * 1. HashSet保证元素唯一性:调用hashSet.add(E e)时底层依赖hashCode()和equals()方法
 *      如果是对象类型元素需要重写hashCode()和equals()方法
 * 2. HashSet如何保证元素唯一性:
 *      先调用hashCode()方法,hashCode相同,然后根据equals()方法判断
 * 3. 注意:Set保证元素唯一性不适用于引用类型,对于引用类型
 *      HashSet:重写equals()和hashCode()方法
 *      HashTable:使用Comparator或者Comparable保证实现元素唯一性
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
@Slf4j
public class HashSetTest {

    @Test
    public void testHashSet() {
        Student s1 = new Student("zs", "18");
        Student s2 = new Student("zs", "18");
        Student s3 = new Student("ls", "18");

        HashSet<Student> students = new HashSet<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        for (Student student : students) {
            log.debug("student: {}", student);
        }
    }
}

@Data
@AllArgsConstructor
class Student {

    private String name;
    private String school;
}
