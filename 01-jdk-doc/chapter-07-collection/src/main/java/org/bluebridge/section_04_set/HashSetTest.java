package org.bluebridge.section_04_set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;

/**
 * HashSet 保证元素唯一性测试
 *
 * 1. HashSet 保证元素唯一性：调用 hashSet.add(E e) 时底层依赖 hashCode() 和 equals() 方法
 *    如果是对象类型元素需要重写 hashCode() 和 equals() 方法
 * 2. HashSet 如何保证元素唯一性：
 *    先调用 hashCode() 方法，hashCode 相同，然后根据 equals() 方法判断
 * 3. 注意：Set 保证元素唯一性不适用于引用类型，对于引用类型
 *    HashSet：重写 equals() 和 hashCode() 方法
 *    HashTable：使用 Comparator 或者 Comparable 保证实现元素唯一性
 *
 * @author lingwh
 * @date 2019/7/9 10:30
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
